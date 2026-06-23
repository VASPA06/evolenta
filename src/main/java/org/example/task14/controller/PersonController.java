package org.example.task14.controller;

import org.example.task14.dto.Message;
import org.example.task14.dto.Person;
import org.example.task14.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/person/{p_id}/message")
    public Optional<Person> addMessage(@PathVariable("p_id") int p_id, @RequestBody Message message) {
        return service.addMeesageToPerson(p_id, message);
    }
    @DeleteMapping("/person/{p_id}/message/{m_id}")
    public HttpStatus deleteMessage(@PathVariable("p_id") int p_id, @PathVariable("m_id") int m_id) {
        return service.deleteMessagesOfPerson(p_id, m_id);
    }

    @GetMapping("/person/{p_id}/message/{m_id}")
    public Optional<Message> getMessageByIdOfPersonId(@PathVariable("p_id") int p_id, @PathVariable("m_id") int m_id) {
        return service.findMessageByIdOfPersonId(p_id, m_id);
    }

    @GetMapping("/person/{p_id}/message")
    public List<Message> getMessageListByPersonId(@PathVariable("p_id") int p_id) {
        return service.findMessageListByPersonId(p_id);
    }
    @GetMapping("/person")
    public List<Person> getAllPersons() {
        return service.findAll();
    }
    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/person")
    public Person save(@RequestBody Person person) {
        return service.save(person);
    }
    @DeleteMapping("/person/{id}")
    public void deleteById(@PathVariable int id) {
        service.removeById(id);
    }
    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updateById(@PathVariable int id, @RequestBody Person person) {
        try {
            return new ResponseEntity<>(service.updateById(id, person), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            person.setId(id);
            return new ResponseEntity<>(service.save(person), HttpStatus.CREATED);
        }
    }
}