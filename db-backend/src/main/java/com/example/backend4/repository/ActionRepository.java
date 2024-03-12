package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Actions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ActionRepository extends JpaRepository<Actions, Long> {

    public Actions findActionById(Long id);

    public Boolean getPositivityById(Long id);

    @Query(value = "SELECT * FROM actions;", nativeQuery = true)
    public List<Actions> findAll();

    @Query("SELECT a FROM Actions a WHERE a.idLetter = :id")
    Actions findByLetterId(@Param("id") Long id);



}
