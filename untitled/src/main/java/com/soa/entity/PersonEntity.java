package com.soa.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

import javax.persistence.*;
@Entity
@Table(name = "persons")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private ZonedDateTime birthday;

    @Getter
    @Setter
    @Column(nullable = false)
    private long height;

    @Getter
    @Setter
    @Column(nullable = false)
    private long weight;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    @Getter
    @Setter
    private LocationEntity location;

}
