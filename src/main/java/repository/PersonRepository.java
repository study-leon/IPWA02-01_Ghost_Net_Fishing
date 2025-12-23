package com.ghostnet.ghostnet.repository;

import com.ghostnet.ghostnet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
