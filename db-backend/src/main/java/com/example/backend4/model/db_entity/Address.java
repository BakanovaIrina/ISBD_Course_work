package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    private long id;
    @Column(name = "country")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "house")
    private String house;
    @Column(name = "room")
    private Integer room;

    public Address() {
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouse() {
        return house;
    }

    public Integer getRoom() {
        return room;
    }
}
