package org.example.task14.controller;

import org.example.task14.dto.Message;
import org.example.task14.repository.MessageRepository;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    private MessageRepository repository;
    private List<Message> messages = new ArrayList<Message>(Arrays.asList(
            new Message("Письмо о любви", "Я тебя люблю", LocalDate.now()),
            new Message("Письмо о ненависти", "Я тебя ненавижу", LocalDate.now()),
            new Message("Письмо о страхе", "Я тебя боюсь", LocalDate.now()))
    );

    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return repository.findAll();
    }
    @GetMapping("/message/{id}")
    public Optional<Message> getMessageById(@PathVariable int id) {
        return repository.findById(id);
    }
    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        repository.save(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        HttpStatus status =  repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity<>(message, status);
    }
    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        Optional<Message> message = getMessageById(id);
        messages.remove(message);
    }
}
