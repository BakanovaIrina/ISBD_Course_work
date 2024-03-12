package com.example.backend4.model.db_entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "Gift_Status")
@Table(name = "gift_status")
public class GiftStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "idgift")
    private long idGift;
    @Column(name = "time")
    private Date time;
    @Column(name = "approval")
    private boolean approval;
    @Column(name = "status_location")
    private String status_location;

    public long getId() {
        return id;
    }

    public long getIdGift() {
        return idGift;
    }

    public Date getTime() {
        return time;
    }

    public boolean isApproval() {
        return approval;
    }

    public String getStatus_location() {
        return status_location;
    }
}
