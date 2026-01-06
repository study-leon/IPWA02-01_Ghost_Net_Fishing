package com.ghostnet.ghostnet.controller;

import com.ghostnet.ghostnet.model.GhostNet;
import com.ghostnet.ghostnet.model.Person;
import com.ghostnet.ghostnet.service.GhostNetService;
import com.ghostnet.ghostnet.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class GhostNetController {

    private final GhostNetService service;
    private final PersonService personService;

    public GhostNetController(GhostNetService service, PersonService personService) {
        this.service = service;
        this.personService = personService;
    }

    // Startseite: alle Netze
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("nets", service.findAll());
        return "list";
    }

    // Neues Netz melden – Formular
    @GetMapping("/add")
    public String showAddForm() {
        return "add-net";
    }

    // Neues Netz melden – speichern
    @PostMapping("/add")
    public String submitAddForm(@RequestParam String gps,
                                @RequestParam String size) {
        GhostNet net = new GhostNet();
        net.setGps(gps);
        net.setSize(size);
        net.setStatus("GEMELDET");
        service.save(net);
        return "redirect:/";
    }

    // Nur offene Netze (Status GEMELDET)
    @GetMapping("/open-nets")
    public String showOpenNets(Model model) {
        model.addAttribute("nets", service.findByStatus("GEMELDET"));
        return "open-nets";
    }

    // Karte der offenen Netze (alle GEMELDET)
    @GetMapping("/map")
    public String showMap(Model model) {
        model.addAttribute("nets", service.findByStatus("GEMELDET"));
        return "map";
    }

    // Formular zum Bergen anzeigen
    @GetMapping("/rescue/{id}")
    public String showRescueForm(@PathVariable Long id, Model model) {
        GhostNet net = service.findById(id);

        // nur bergende Personen anbieten
        List<Person> persons = personService.findAll().stream()
                .filter(p -> "BERGEND".equals(p.getRole()))
                .toList();

        model.addAttribute("net", net);
        model.addAttribute("persons", persons);
        return "rescue";
    }

    // Bergung eintragen
    @PostMapping("/rescue")
    public String submitRescueForm(@RequestParam Long netId,
                                   @RequestParam Long personId) {

        GhostNet net = service.findById(netId);
        Person p = personService.findById(personId);

        // nur offene Netze -> Bergung bevorstehend
        if (net != null && p != null && "GEMELDET".equals(net.getStatus())) {
            net.setRescuer(p);
            net.setStatus("BERGUNG_BEVORSTEHEND");
            service.save(net);
        }

        return "redirect:/";
    }

    // Formular: Netz als verschollen melden
    @GetMapping("/missing/{id}")
    public String showMissingForm(@PathVariable Long id, Model model) {
        GhostNet net = service.findById(id);
        model.addAttribute("net", net);
        model.addAttribute("persons", personService.findAll());
        return "missing";
    }

    // Netz als verschollen markieren (nicht anonym, Person muss gewählt werden)
    @PostMapping("/missing")
    public String submitMissingForm(@RequestParam Long netId,
                                    @RequestParam Long personId) {

        GhostNet net = service.findById(netId);

        if (net != null && !"GEBORGEN".equals(net.getStatus())) {
            net.setStatus("VERSCHOLLEN");
            service.save(net);
        }

        return "redirect:/";
    }

    // Netz als endgültig geborgen markieren
    @GetMapping("/done/{id}")
    public String markAsDone(@PathVariable Long id) {
        GhostNet net = service.findById(id);
        if (net != null && "BERGUNG_BEVORSTEHEND".equals(net.getStatus())) {
            net.setStatus("GEBORGEN");
            service.save(net);
        }
        return "redirect:/";
    }

    // Übersicht: bergende Personen & ihre (zur Bergung übernommenen) Netze
    @GetMapping("/rescuer-list")
    public String showRescuerList(Model model) {

        // nur Netze, für die eine Bergung geplant ist
        List<GhostNet> nets = service.findByStatus("BERGUNG_BEVORSTEHEND");

        Map<Person, List<GhostNet>> groups = nets.stream()
                .filter(n -> n.getRescuer() != null)
                .collect(Collectors.groupingBy(GhostNet::getRescuer));

        model.addAttribute("groups", groups);
        return "rescuer-list";
    }
}
