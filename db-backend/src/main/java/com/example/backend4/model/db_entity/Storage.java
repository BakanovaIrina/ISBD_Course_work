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

    public Storage(long cellnumber, long idGift) {
        this.cellnumber = cellnumber;
        this.idGift = idGift;
    }

    public Storage() {
    }

    public long getCellnumber() {
        return cellnumber;
    }

    public long getIdGift() {
        return idGift;
    }


}
