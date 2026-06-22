package org.example.task14.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String text;
    private LocalDate time;

    @ManyToOne
    @JsonIgnore
    private Person person;

    public Message() {}

    public Message(int id, String title, String text, LocalDate time, Person person) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
        this.person = person;
    }

    public Message(String title, String text, LocalDate time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }
}