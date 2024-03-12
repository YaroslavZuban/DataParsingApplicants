package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataWriter {
    private final PersonDataServiceImpl personDataService;

    @Autowired
    public DataWriter(PersonDataServiceImpl personDataService) {
        this.personDataService = personDataService;
    }

    public void writeDataBase(Map<String, List<String>> resume) {
        personDataService.processPersonalDataInformation(resume);
    }
}