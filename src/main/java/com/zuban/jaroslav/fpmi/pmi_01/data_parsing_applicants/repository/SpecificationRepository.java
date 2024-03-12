package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {
    @Query("SELECT s FROM Specification s " +
            "WHERE s.ending =:ending AND " +
            "s.educationalInstitution =:educationalInstitution AND " +
            "s.direction = :direction AND " +
            "s.educationType.id =:educationTypeId")
    Specification findMatchingSpecifications(
            @Param("ending") int ending,
            @Param("educationalInstitution") String educationalInstitution,
            @Param("direction") String direction,
            @Param("educationTypeId") int educationTypeId
    );

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM Specification s " +
            "WHERE s.ending = :ending AND " +
            "s.educationalInstitution = :educationalInstitution AND " +
            "s.direction = :direction AND " +
            "s.educationType.id = :educationTypeId")
    boolean existsBySpecifications(
            @Param("ending") int ending,
            @Param("educationalInstitution") String educationalInstitution,
            @Param("direction") String direction,
            @Param("educationTypeId") int educationTypeId);
}