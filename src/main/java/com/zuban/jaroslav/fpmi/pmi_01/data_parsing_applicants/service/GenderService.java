package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Gender;
import org.springframework.stereotype.Service;

@Service
public interface GenderService {
    void save(Gender newGender);

    boolean isExist(Gender gender);
}
