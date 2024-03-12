package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

@Entity
@Table(name = "storage")
public class Storage {

    @Id
    @Column(name = "cellnumber")
    private long cellnumber;

    @Column(name = "idgift")
    private long idGift;

    public long getCellnumber() {
        return cellnumber;
    }

    public long getIdGift() {
        return idGift;
    }


}
