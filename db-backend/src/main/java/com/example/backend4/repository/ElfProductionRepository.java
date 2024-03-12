package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Elf_production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElfProductionRepository extends JpaRepository<Elf_production, Long> {
}
