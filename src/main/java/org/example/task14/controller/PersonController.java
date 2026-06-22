package org.example.task14.controller;


import org.example.task14.PersonRepository;
import org.example.task14.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return  new ResponseEntity<>(repository.save(person), status);
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
