package com.soa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;

    private String name;

    private Coordinates coordinates;

    private LocalDateTime creationDate = LocalDateTime.now();

    private double price;

    private int manufactureCost;

    private UnitOfMeasure unitOfMeasure;
    private Person owner;
}

