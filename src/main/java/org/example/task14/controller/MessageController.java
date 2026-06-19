package org.example.task14.controller;

import org.example.task14.dto.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MessageController {
    private List<Message> messages;
    @GetMapping("/message")
    public Iterable<Message> getMessages() {
        return messages;
    }
    @GetMapping("/message/{id}")
    public Optional<Message> getMessageById(@PathVariable int id) {
        return messages.stream().filter(m -> m.getId() == id).findFirst();
    }
    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        messages.add(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PutMapping("/message/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message message) {
        int index = -1;
        for(Message m : messages) {
            if (m.getId() == id) {
                index = messages.indexOf(m);
                messages.set(index, message);
            }
        }
        return index == -1 ? addMessage(message)
                : new ResponseEntity<>(message, HttpStatus.OK);
    }
    @DeleteMapping("/message/{id}")
    public void deleteMessage(@PathVariable int id) {
        Optional<Message> message = getMessageById(id);
        messages.remove(message);
    }
}
