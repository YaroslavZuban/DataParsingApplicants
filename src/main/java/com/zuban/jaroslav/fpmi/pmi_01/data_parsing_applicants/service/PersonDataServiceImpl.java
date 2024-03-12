package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EmployeesExperience;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Information;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.PersonalData;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final PersonalDataRepository personalDataRepository;
    private final InformationServiceImpl informationService;
    private final EmployeesExperienceServiceImpl employeesExperienceService;

    @Autowired
    public PersonDataServiceImpl(PersonalDataRepository personalDataRepository,
                                 InformationServiceImpl informationService,
                                 EmployeesExperienceServiceImpl employeesExperienceService) {
        this.personalDataRepository = personalDataRepository;
        this.informationService = informationService;
        this.employeesExperienceService = employeesExperienceService;
    }

    @Override
    public void save(Map<String, String> newPersonalData) {

        PersonalData personalData = new PersonalData();
    }

    @Override
    public boolean isExist(PersonalData personalData) {
        return false;
    }

    public PersonalData processPersonalDataInformation(Map<String, List<String>> resume) {
        String name = resume.get("Имя").get(0);
        String title = resume.get("Title").get(0);

        // Создаем DateTimeFormatter с указанием нужного шаблона и локали
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));

        // Парсим строку в объект LocalDate с помощью DateTimeFormatter
        LocalDate date = LocalDate.parse(data, formatter);

        String wages = resume.get("Заработная плата").get(0);

        PersonalData personalData = new PersonalData(name, title);

        Information information = informationService.processInformation(resume);
        personalData.setInformation(information);

        List<EmployeesExperience> employeesExperienceList =
                employeesExperienceService.processEmployeesExperienceInformation(resume);
        personalData.setExperienceList(employeesExperienceList);


    }

    private PersonalData processNewPersonalDataInformation(Map<String, List<String>> resume) {
        String name = resume.get("Имя").get(0);
        String title = resume.get("Title").get(0);

        // Создаем DateTimeFormatter с указанием нужного шаблона и локали
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("ru"));

        // Парсим строку в объект LocalDate с помощью DateTimeFormatter
        LocalDate date = LocalDate.parse(data, formatter);

        String wages = resume.get("Заработная плата").get(0);

        PersonalData personalData = new PersonalData(name, title);
    }
}
