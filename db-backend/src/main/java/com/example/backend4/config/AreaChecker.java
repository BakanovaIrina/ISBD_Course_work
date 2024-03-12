package com.example.backend4.config;

import com.example.backend4.model.AreaFunction;

import java.util.Arrays;
import java.util.Collection;

public final class AreaChecker {

	private static final Collection<AreaFunction> areas = Arrays.asList(
			(x, y, r) -> // Прямоугольник
			x >= 0 && y >= 0 && y <= r/2 && x <= r,
			(x, y, r) -> // Треугольник
			x >= 0 && y <= 0 && y >= 2*x -r,
			(x, y, r) -> // Круг
			x <= 0 && y >= 0 && x * x + y * y <= r * r / 4);

	private AreaChecker() {
	}

	public static final Collection<AreaFunction> getAreas() {
		return areas;
	}

}
