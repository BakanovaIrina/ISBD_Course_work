package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity
@Table(name = "actions")
public class Actions {

    @Id
    private long id;
    @Column(name = "idletter")
    private long idLetter;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "positivity")
    private boolean positivity;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public long getIdLetter() {
        return idLetter;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPositivity() {
        return positivity;
    }

    public void setName(String name) {
        this.name = name;
    }
}
