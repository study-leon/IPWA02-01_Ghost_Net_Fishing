package com.ghostnet.ghostnet.repository;

import com.ghostnet.ghostnet.model.GhostNet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GhostNetRepository extends JpaRepository<GhostNet, Long> {

    List<GhostNet> findByStatus(String status);
    List<GhostNet> findByStatusAndRescuerIsNotNull(String status);
}
