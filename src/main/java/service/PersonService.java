package com.ghostnet.ghostnet.service;

import com.ghostnet.ghostnet.model.Person;
import com.ghostnet.ghostnet.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    public Person save(Person p) {
        return repo.save(p);
    }

    public List<Person> findAll() {
        return repo.findAll();
    }

    public Person findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
