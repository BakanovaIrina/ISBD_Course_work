package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity
@Table(name = "production")
public class Production {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "idgift")
    private long idGift;
    @Column(name = "workstatus")
    private String workstatus;

    public Production(long id, long idGift, String workstatus) {
        this.id = id;
        this.idGift = idGift;
        this.workstatus = workstatus;
    }

    public Production() {
    }

    public long getId() {
        return id;
    }

    public long getIdGift() {
        return idGift;
    }

    public String getWorkstatus() {
        return workstatus;
    }
}
