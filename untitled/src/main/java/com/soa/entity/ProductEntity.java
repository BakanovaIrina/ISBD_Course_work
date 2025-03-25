package com.soa.entity;

import com.soa.model.UnitOfMeasure;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer id;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "coordinates_id", nullable = false)
    @Getter
    @Setter
    private CoordinatesEntity coordinates;

    @Column(nullable = false, updatable = false)
    @Getter
    @Setter
    private LocalDateTime creationDate;

    @Column(nullable = false)
    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private int manufactureCost;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private UnitOfMeasure unitOfMeasure;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    @Getter
    @Setter
    private PersonEntity owner;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
    }
}

