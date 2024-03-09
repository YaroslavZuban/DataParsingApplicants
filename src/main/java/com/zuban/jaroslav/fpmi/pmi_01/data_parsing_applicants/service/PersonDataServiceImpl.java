package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EmployeesExperience;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.PersonalData;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final PersonalDataRepository personalDataRepository;

    @Autowired
    public PersonDataServiceImpl(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Override
    public void save(Map<String, String> newPersonalData) {

        PersonalData personalData = new PersonalData();
    }

    @Override
    public boolean isExist(PersonalData personalData) {
        return false;
    }

  /*  private PersonalData addNewPersonData(Map<String,String> newPersonData){
        String name = newPersonData.get("Имя");
        PersonalData personalData = new PersonalData(name);

        // опыт работы
        List<String> posts = newPersonData.get("Должность");
        List<String> responsibilitiesList = newPersonData.get("Обязанности");
        List<String> companies= newPersonData.get("Компания");

        for (int i = 0; i < posts.size(); i++) {
            String pots=null;

            if(i<posts.size()){
                posts= Collections.singletonList(posts.get(i));
            }
        }


        return null;
    }*/
}
