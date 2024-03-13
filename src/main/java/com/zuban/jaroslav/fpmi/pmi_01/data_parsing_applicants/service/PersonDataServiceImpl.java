package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final PersonalDataRepository personalDataRepository;
    private final HabitationServiceImpl habitationService;
    private final GenderServiceImpl genderService;
    private final InformationServiceImpl informationService;
    private final EmployeesExperienceServiceImpl employeesExperienceService;
    private final WorkScheduleServiceImpl workScheduleService;
    private final SpecificationServiceImpl specificationService;
    private final CitizenshipTypeServiceImpl citizenshipTypeService;

    @Autowired
    public PersonDataServiceImpl(PersonalDataRepository personalDataRepository,
                                 InformationServiceImpl informationService,
                                 EmployeesExperienceServiceImpl employeesExperienceService,
                                 HabitationServiceImpl habitationService,
                                 GenderServiceImpl genderService,
                                 WorkScheduleServiceImpl workScheduleService,
                                 SpecificationServiceImpl specificationService,
                                 CitizenshipTypeServiceImpl citizenshipTypeService) {
        this.personalDataRepository = personalDataRepository;
        this.informationService = informationService;
        this.employeesExperienceService = employeesExperienceService;
        this.habitationService = habitationService;
        this.genderService = genderService;
        this.workScheduleService = workScheduleService;
        this.specificationService = specificationService;
        this.citizenshipTypeService = citizenshipTypeService;
    }

    @Override
    public void save(PersonalData newPersonalData) {
        personalDataRepository.save(newPersonalData);
    }

    @Override
    public boolean isExist(PersonalData personalData) {
        return personalDataRepository.existsByNameAndTitleAndWagesAndHabitationAndGenderAndBirthDataAndInformation(
                personalData.getName(),
                personalData.getTitle(),
                personalData.getWages(),
                personalData.getHabitation(),
                personalData.getGender(),
                personalData.getBirthData(),
                personalData.getInformation()
        );
    }

    public void processPersonalDataInformation(Map<String, List<String>> resume) {
        PersonalData personalData = processNewPersonalDataInformation(resume);

        Habitation habitation = habitationService.processHabitationInformation(resume);
        personalData.setHabitation(habitation);

        Gender gender = genderService.processGenderInformation(resume);
        personalData.setGender(gender);

        Information information = informationService.processInformation(resume);
        personalData.setInformation(information);

        List<EmployeesExperience> employeesExperienceList =
                employeesExperienceService.processEmployeesExperienceInformation(resume);
        personalData.setExperienceList(employeesExperienceList);

        List<WorkSchedule> workScheduleList =
                workScheduleService.processWorkScheduleInformation(resume);
        personalData.setWorkSchedule(workScheduleList);

        List<Specification> specificationList =
                specificationService.processSpecificationInformation(resume);
        personalData.setSpecifications(specificationList);

        List<CitizenshipType> citizenshipTypeList =
                citizenshipTypeService.processCitizenshipTypesInformation(resume);
        personalData.setCitizenshipType(citizenshipTypeList);

        save(personalData);
    }

    private PersonalData processNewPersonalDataInformation(Map<String, List<String>> resume) {
        String name;

        if (resume.get("Имя") == null) {
            name = "NaN";
        } else {
            name = resume.get("Имя").get(0);
        }

        String title = null;

        if (resume.get("Title") != null) {
            title = resume.get("Title").get(0);
        }

        Date age = null;

        if (resume.get("Возраст") != null) {
            // Создаем DateTimeFormatter с указанием нужного шаблона и локали
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));

            String birthsData = resume.get("Возраст").get(0);

            int startIndex = birthsData.indexOf("(") + 1;
            int endIndex = birthsData.indexOf(")");

            birthsData = birthsData.substring(startIndex, endIndex);

            // Парсим строку в объект LocalDate с помощью DateTimeFormatter
            LocalDate date = LocalDate.parse(birthsData, formatter);

            age = java.sql.Date.valueOf(date);
        }

        String wagesStr;
        Integer wages = null;

        if (resume.get("Заработная плата") != null) {
            wagesStr = resume.get("Заработная плата").get(0);

            if (wagesStr.equals("По договоренности")) {
                wagesStr = null;
            } else {
                wagesStr = wagesStr.replaceAll("[^\\d\\s]", "").replaceAll("\\s", "");
            }

            if (wagesStr != null && !wagesStr.isEmpty()) {
                wages = Integer.parseInt(wagesStr);
            }
        }

        return new PersonalData(name, title, wages, age);
    }
}