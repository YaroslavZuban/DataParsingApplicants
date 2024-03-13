package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Integer> {
    boolean existsByNameAndTitleAndWagesAndHabitationAndGenderAndBirthDataAndInformation(
            String name,
            String title,
            Integer wages,
            Habitation habitation,
            Gender gender,
            Date birthData,
            Information information
    );
}
