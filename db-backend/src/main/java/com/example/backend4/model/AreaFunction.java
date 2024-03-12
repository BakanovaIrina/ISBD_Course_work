package com.example.backend4.model;

@FunctionalInterface
public interface AreaFunction {
    boolean inArea(double x, double y, double r);
}
