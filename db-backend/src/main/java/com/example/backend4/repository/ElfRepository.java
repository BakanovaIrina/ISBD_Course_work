package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Elf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ElfRepository extends JpaRepository<Elf, Long> {
    @Query(value = "SELECT assign_elfs_to_production(?1, ?2)", nativeQuery = true)
    void assignElfsToProduction(int elf_id, int prod_id);
}
