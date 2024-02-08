package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Information;
import org.springframework.stereotype.Service;

@Service
public interface InformationService {
    void save(Information newInformation);

    boolean isExist(Information information);
}
