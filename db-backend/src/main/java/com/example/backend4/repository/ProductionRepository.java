package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductionRepository extends JpaRepository<Production, Long> {

    @Transactional
    @Query(value = "SELECT add_gift_to_production(?1)", nativeQuery = true)
    void addGiftToProduction(int giftId);

    @Transactional
    @Query(value = "SELECT complete_production(?1)", nativeQuery = true)
    void completeProduction(int giftId);
}
