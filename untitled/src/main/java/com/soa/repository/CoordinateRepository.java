package com.soa.repository;

import com.soa.entity.CoordinatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoordinateRepository extends JpaRepository<CoordinatesEntity, Integer> {
}
