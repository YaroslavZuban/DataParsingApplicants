package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.PersonalData;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDataServiceImpl implements PersonDataService {
    private final PersonalDataRepository personalDataRepository;

    @Autowired
    public PersonDataServiceImpl(PersonalDataRepository personalDataRepository) {
        this.personalDataRepository = personalDataRepository;
    }

    @Override
    public void save(PersonalData newPersonalData) {

    }

    @Override
    public boolean isExist(PersonalData personalData) {
        return false;
    }
}
