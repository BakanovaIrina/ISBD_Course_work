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

    public Elf_production(long id, long idElf, long idProduction) {
        this.id = id;
        this.idElf = idElf;
        this.idProduction = idProduction;
    }

    public Elf_production() {
    }

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
