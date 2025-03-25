package com.soa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewProduct {

    private String name;

    private Coordinates coordinates;

    private double price;

    private int manufactureCost;

    private UnitOfMeasure unitOfMeasure;

    private Person owner;
}
