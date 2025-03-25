package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "elf_status")
public class ElfStatus {

    @Id
    private long id;

    @Column(name = "idelf")
    private long idElf;

    @Column(name = "time")
    private Date time;

    @Column(name = "rest")
    private boolean rest;

    public ElfStatus(long id, long idElf, Date time, boolean rest) {
        this.id = id;
        this.idElf = idElf;
        this.time = time;
        this.rest = rest;
    }

    public ElfStatus() {
    }

    public long getId() {
        return id;
    }

    public long getIdElf() {
        return idElf;
    }

    public Date getTime() {
        return time;
    }

    public boolean isRest() {
        return rest;
    }
}
