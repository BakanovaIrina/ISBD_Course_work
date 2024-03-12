package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    public Address findAddressById(Long id);
}
