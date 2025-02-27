package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity
@Table(name = "child")
public class Child {
    @Id
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public long getId() {
        return id;
    }
}
