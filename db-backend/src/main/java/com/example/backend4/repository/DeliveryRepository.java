package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Transactional
    @Query(value = "SELECT move_gifts_to_delivery()", nativeQuery = true)
    void moveGiftToDelivery();

}
