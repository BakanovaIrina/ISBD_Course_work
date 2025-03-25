package com.soa.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "coordinates")
public class CoordinatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Getter
    @Setter
    private Double x;

    @NotNull
    @Column(nullable = false)
    @Getter
    @Setter
    private Float y;
}

