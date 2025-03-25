package com.soa.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class LocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    @Setter
    private double x;

    @Column(nullable = false)
    @Getter
    @Setter
    private double y;

    @NotNull
    @Column(nullable = false, length = 940)
    @Getter
    @Setter
    private String name;
}

