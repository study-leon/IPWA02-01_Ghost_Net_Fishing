package com.ghostnet.ghostnet.controller;

import com.ghostnet.ghostnet.model.Person;
import com.ghostnet.ghostnet.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/add-person")
    public String showAddPersonForm() {
        return "add-person";
    }

    @PostMapping("/add-person")
    public String addPerson(@RequestParam String name,
                            @RequestParam String role,
                            @RequestParam(required = false) String phone,
                            Model model) {

        // bergende Person MUSS Telefonnummer haben
        if ("BERGEND".equals(role) &&
                (phone == null || phone.trim().isEmpty())) {
            model.addAttribute("error",
                    "Bergende Personen müssen eine Telefonnummer angeben.");
            return "add-person";
        }

        Person p = new Person();
        p.setName(name);
        p.setRole(role);                   // "MELDEND" oder "BERGEND"
        p.setPhone(phone != null ? phone.trim() : "");

        personService.save(p);

        return "redirect:/";
    }
}
