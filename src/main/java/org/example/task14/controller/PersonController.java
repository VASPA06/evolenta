package org.example.task14.controller;


import org.example.task14.dto.Person;
import org.example.task14.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;
    @GetMapping("/person")
    public Iterable<Person> getPersons() {
        return repository.findAll();
    }
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        repository.save(person);
        return person;
    }
    @PutMapping("/person/{id}")
    public ResponseEntity<Person>updatePerson(@PathVariable int id, @RequestBody Person person) {
        return repository.findById(id).map(p -> {
            p.setId(id);
            p.setFirstname(person.getFirstname());
            p.setSurname(person.getSurname());
            p.setLastname(person.getLastname());
            p.setBirthday(person.getBirthday());
            repository.save(p);
            return new ResponseEntity<>(p, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(person, HttpStatus.CREATED));
    }
    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id) {
        return repository.findById(id);
    }
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id) {
        repository.deleteById(id);
    }
}
