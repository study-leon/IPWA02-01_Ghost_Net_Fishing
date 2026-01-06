package com.ghostnet.ghostnet.service;

import com.ghostnet.ghostnet.model.GhostNet;
import com.ghostnet.ghostnet.repository.GhostNetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GhostNetService {

    private final GhostNetRepository repo;

    public GhostNetService(GhostNetRepository repo) {
        this.repo = repo;
    }

    public GhostNet save(GhostNet net) {
        return repo.save(net);
    }

    public List<GhostNet> findAll() {
        return repo.findAll();
    }

    public List<GhostNet> findByStatus(String status) {
        return repo.findByStatus(status);
    }

    public GhostNet findById(Long id) {
        return repo.findById(id).orElse(null);
    }
    public List<GhostNet> findAllWithRescuer() {
        return repo.findAll()
                .stream()
                .filter(n -> n.getRescuer() != null)
                .toList();
    }
}
