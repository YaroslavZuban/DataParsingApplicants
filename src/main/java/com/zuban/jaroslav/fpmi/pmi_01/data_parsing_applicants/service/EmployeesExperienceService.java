package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EmployeesExperience;
import org.springframework.stereotype.Service;

@Service
public interface EmployeesExperienceService {
    void save(EmployeesExperience newEmployeesExperience);

    boolean isExist(EmployeesExperience employeesExperience);
}
