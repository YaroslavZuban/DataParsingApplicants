package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {

    @Query("SELECT i FROM Information i " +
            "WHERE i.courses =:courses AND " +
            "i.skills =:skills AND " +
            "i.aboutMe = :aboutMe AND " +
            "i.businessTrips.id =:businessTripsId")
    boolean existsByInformation(
            @Param("courses") String courses,
            @Param("skills") String skills,
            @Param("aboutMe") String aboutMe,
            @Param("businessTripsId") int businessTripsId
    );
}
