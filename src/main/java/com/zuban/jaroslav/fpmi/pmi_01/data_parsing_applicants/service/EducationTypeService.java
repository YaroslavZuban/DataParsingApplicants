package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EducationType;
import org.springframework.stereotype.Service;

@Service
public interface EducationTypeService {
    void save(EducationType newEducationType);

    boolean isExist(EducationType educationType);
}
