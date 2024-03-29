package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EmployeesExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesExperienceRepository extends JpaRepository<EmployeesExperience, Integer> {
    boolean existsByPostAndResponsibilitiesAndCompany(String post, String responsibilities, String company);

    EmployeesExperience findByPostAndResponsibilitiesAndCompany(String post, String responsibilities, String company);
}
