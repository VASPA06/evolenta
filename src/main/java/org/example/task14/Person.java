package org.example.task14;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Person {
    private int id;
    private String firstname;
    private String surname;
    private String lastname;
    private LocalDate birthday;
    public Person(int id, String firstname, String surname, String lastname, LocalDate birthday) {

        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.birthday = birthday;
    }
}
