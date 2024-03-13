package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformationRepository extends JpaRepository<Information, Integer> {
    boolean existsByCoursesAndSkillsAndAboutMeAndBusinessTrips(
            String courses,
            String skills,
            String aboutMe,
            BusinessTrips businessTrips
    );
}
