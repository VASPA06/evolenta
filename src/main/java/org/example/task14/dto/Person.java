package org.example.task14.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messageList = new ArrayList<>();
    public Person() {}
    public Person(String firstname, String surname, String lastname, LocalDate birthday, List<Message> messageList) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.messageList = messageList != null ? messageList : new ArrayList<>();
    }

    public Person(int id, String firstname, String surname, String lastname, LocalDate birthday) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public void addMessage(Message message) {
        messageList.add(message);
        message.setPerson(this);
    }

    public void deleteMessageById(int messageId) {
        messageList.removeIf(m -> m.getId() == messageId);
    }

    public Optional<Message> findMessageById(int id) {
        return messageList.stream().filter(m -> m.getId() == id).findFirst();
    }
}