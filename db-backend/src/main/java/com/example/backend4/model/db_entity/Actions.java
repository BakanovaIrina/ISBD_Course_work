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

    public Actions(long idLetter, String name, String description, boolean positivity) {
        this.idLetter = idLetter;
        this.name = name;
        this.description = description;
        this.positivity = positivity;
    }

    public Actions() {

    }

    public long getIdLetter() {
        return idLetter;
    }

    public void setIdLetter(long idLetter) {
        this.idLetter = idLetter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPositivity() {
        return positivity;
    }

    public void setPositivity(boolean positivity) {
        this.positivity = positivity;
    }

    public long getId() {
        return id;
    }
}
