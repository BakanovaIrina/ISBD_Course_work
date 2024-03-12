package com.example.backend4.model.db_entity;

import jakarta.persistence.*;
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    private long id;

    @Column(name = "idgift")
    private long idGift;
    @Column(name = "idchild")
    private long idChild;
    @Column(name = "idaddress")
    private long idAddress;
    @Column(name = "place")
    private String place;

    public long getId() {
        return id;
    }

    public long getIdGift() {
        return idGift;
    }

    public long getIdChild() {
        return idChild;
    }

    public long getIdAddress() {
        return idAddress;
    }

    public String getPlace() {
        return place;
    }
}
