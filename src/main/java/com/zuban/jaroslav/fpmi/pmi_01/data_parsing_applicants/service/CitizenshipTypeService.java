package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.CitizenshipType;
import org.springframework.stereotype.Service;

@Service
public interface CitizenshipTypeService {
    void save(CitizenshipType newCitizenshipType);

    boolean isExist(CitizenshipType citizenshipType);
}
