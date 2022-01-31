package com.naname.carddirectory.data;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.awt.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    private String number;

    private String model;
    private int year;
    private String color;
    private String body;
    private LocalDateTime date = LocalDateTime.now();

    public Car(String number, String model, int year, String color, String body, LocalDateTime date) {
        this.number = number;
        this.model = model;
        this.year = year;
        this.color = color;
        this.body = body;
        this.date = date;
    }
}
