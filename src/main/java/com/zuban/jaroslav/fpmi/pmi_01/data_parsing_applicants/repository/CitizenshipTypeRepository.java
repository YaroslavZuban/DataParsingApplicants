package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.CitizenshipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenshipTypeRepository extends JpaRepository<CitizenshipType, Integer> {
    boolean existsByName(String name);

    CitizenshipType findByName(String name);
}
