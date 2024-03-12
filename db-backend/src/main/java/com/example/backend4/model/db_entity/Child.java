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

    public Child(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Child() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public long getId() {
        return id;
    }
}
