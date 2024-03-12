package com.example.backend4.repository;

import com.example.backend4.model.response.AreaCheckResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<AreaCheckResponse, Long> {
    public List<AreaCheckResponse> findAllByOwnerId(long ownerId);
}
