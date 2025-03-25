package com.soa.model;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
public class Person {
    @Generated
    private Long id;

    private String name;

    private ZonedDateTime birthday; // Может быть null

    private long height;

    private long weight;

    private Location location; // Может быть null

    public Person(Long id, String name, ZonedDateTime birthday, long height, long weight, Location location) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.location = location;
    }
}

