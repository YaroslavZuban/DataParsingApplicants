package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationTypeRepository extends JpaRepository<EducationType, Integer> {
    EducationType findByEducationLevel(String educationLevel);

    boolean existsByEducationLevel(String educationLevel);
}
