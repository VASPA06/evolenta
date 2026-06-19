package org.example.task14.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@Getter
@Setter
public class Message {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String text;
    private LocalDate time;
    public Message() {}
    public Message(int id, String title, String text, LocalDate time) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
    }
    public Message(String title, String text, LocalDate time) {
        this.title = title;
        this.text = text;
        this.time = time;
    }
}
