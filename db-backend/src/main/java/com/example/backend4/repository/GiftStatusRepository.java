package com.example.backend4.repository;

import com.example.backend4.model.db_entity.GiftStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftStatusRepository extends JpaRepository<GiftStatus, Long> {
}
