package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity(name = "Elf_production")
@Table(name = "elf_production")
public class Elf_production {

    @Id
    private long id;

    @Column(name = "idelf")
    private long idElf;

    @Column(name = "idproduction")
    private long idProduction;

    public long getId() {
        return id;
    }

    public long getIdElf() {
        return idElf;
    }

    public long getIdProduction() {
        return idProduction;
    }
}
