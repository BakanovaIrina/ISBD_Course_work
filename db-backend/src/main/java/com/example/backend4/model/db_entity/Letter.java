package com.example.backend4.model.db_entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "letter")
public class Letter {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "idchild")
    private long idChild;

    @Column(name = "idgift")
    private long idGift;

    @Column(name = "idaddress")
    private long idAddress;

    @Column(name = "truth")
    private boolean truth;

    public long getId() {
        return id;
    }

    public long getIdChild() {
        return idChild;
    }

    public long getIdGift() {
        return idGift;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public boolean isTruth() {
        return truth;
    }
}
