package com.example.backend4.repository;

import com.example.backend4.model.db_entity.Letter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LetterRepository extends JpaRepository<Letter, Long> {
    @Query(value = "SELECT * FROM letter;", nativeQuery = true)
    List<Letter> findAll();

    @Transactional
    @Query(value = "SELECT add_letter("
            + ":childName, "
            + ":childSurname, "
            + ":country1, "
            + ":region1, "
            + ":city1, "
            + ":street1, "
            + ":house1, "
            + ":room1, "
            + ":giftName, "
            + "ARRAY[:actions], "
            + "ARRAY[:descriptions], "
            + ":truth1, "
            + ":approval1, "
            + "ARRAY[:positivities])", nativeQuery = true)

    void callAddLetterFunction(
            String childName,
            String childSurname,
            String country1,
            String region1,
            String city1,
            String street1,
            String house1,
            Integer room1,
            String giftName,
            String actions,
            String descriptions,
            Boolean truth1,
            Boolean approval1,
            Boolean positivities);
}
