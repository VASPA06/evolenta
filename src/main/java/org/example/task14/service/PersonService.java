package org.example.task14.service;

import org.example.task14.dto.Message;
import org.example.task14.dto.Person;
import org.example.task14.repository.MessageRepository;
import org.example.task14.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private MessageRepository messageRepository;

    public Optional<Person> addMeesageToPerson(int personId, Message message) {
        Optional<Person> person = repository.findById(personId);
        if (person.isPresent()) {
            Person newPerson = person.get();
            message.setTime(LocalDate.now());
            newPerson.addMessage(message);
            return Optional.of(repository.save(newPerson));
        } else {
            return Optional.empty();
        }

    }
    public HttpStatus deleteMessagesOfPerson(int personId, int messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    public Optional<Message> findMessageByIdOfPersonId(int personId, int messageId) {
        return repository.findById(personId)
                .flatMap(person -> person.findMessageById(messageId));
    }

    public List<Message> findMessageListByPersonId(int personId) {
        return repository.findById(personId)
                .map(Person::getMessageList)
                .orElse(Collections.emptyList());
    }
    public List<Person> findAll() {
        return repository.findAll();
    }
    public Person findById(int id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found person by id:" + id));
    }
    public Person save(Person person) {
        return repository.save(person);
    }
    public void removeById(int id) {
        repository.deleteById(id);
    }
    public Person updateById(int id, Person person) {
        return repository.findById(id).map(oldPerson -> {
            oldPerson.setFirstname(person.getFirstname());
            oldPerson.setSurname(person.getSurname());
            oldPerson.setLastname(person.getLastname());
            oldPerson.setMessageList(person.getMessageList());
            oldPerson.setBirthday(person.getBirthday());
            return repository.save(oldPerson);
        }).orElseThrow(() -> new NoSuchElementException("Not found person by id:" + id));
    }
}