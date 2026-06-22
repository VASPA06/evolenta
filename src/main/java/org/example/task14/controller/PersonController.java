package org.example.task14.controller;

import org.example.task14.dto.Message;
import org.example.task14.dto.Person;
import org.example.task14.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
}