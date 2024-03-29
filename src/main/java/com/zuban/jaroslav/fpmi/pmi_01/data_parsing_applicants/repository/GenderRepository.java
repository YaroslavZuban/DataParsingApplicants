package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
    boolean existsByType(String type);

    Gender findByType(String type);
}
