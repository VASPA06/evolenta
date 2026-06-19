package org.example.task14.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@Getter
@Setter
public class Message {
    private int id;
    private String title;
    private String text;
    private LocalDate time;
    public Message(int id, String title, String text, LocalDate time) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.time = time;
    }

}
