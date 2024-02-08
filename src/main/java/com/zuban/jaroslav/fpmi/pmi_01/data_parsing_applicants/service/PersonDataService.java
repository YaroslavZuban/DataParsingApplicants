package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.PersonalData;
import org.springframework.stereotype.Service;

@Service
public interface PersonDataService {
    void save(PersonalData newPersonalData);

    boolean isExist(PersonalData personalData);
}
