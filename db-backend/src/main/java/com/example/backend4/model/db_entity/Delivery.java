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

    public Delivery(long id, long idGift, long idChild, long idAddress, String place) {
        this.id = id;
        this.idGift = idGift;
        this.idChild = idChild;
        this.idAddress = idAddress;
        this.place = place;
    }

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
