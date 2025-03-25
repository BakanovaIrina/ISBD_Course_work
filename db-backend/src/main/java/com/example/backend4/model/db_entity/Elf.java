package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity(name = "Elf")
@Table(name = "elf")
public class Elf {
    @Id
    private long id;

    @Column(name = "name")
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Elf(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Elf() {
    }
}
