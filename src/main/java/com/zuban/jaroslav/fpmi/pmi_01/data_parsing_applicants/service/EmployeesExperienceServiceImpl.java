package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EmployeesExperience;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.EmployeesExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeesExperienceServiceImpl implements EmployeesExperienceService {
    private final EmployeesExperienceRepository employeesExperienceRepository;

    @Autowired
    public EmployeesExperienceServiceImpl(EmployeesExperienceRepository employeesExperienceRepository) {
        this.employeesExperienceRepository = employeesExperienceRepository;
    }

    @Override
    public void save(EmployeesExperience newEmployeesExperience) {
        if (isExist(newEmployeesExperience)) {
            return;
        }

        employeesExperienceRepository.save(newEmployeesExperience);
    }

    @Override
    public boolean isExist(EmployeesExperience employeesExperience) {
        return employeesExperienceRepository.existsByPostAndResponsibilitiesAndCompany(
                employeesExperience.getPost(),
                employeesExperience.getResponsibilities(),
                employeesExperience.getCompany());
    }

    public EmployeesExperience find(String post, String responsibilities, String company) {
        return employeesExperienceRepository.findByPostAndResponsibilitiesAndCompany(
                post,
                responsibilities,
                company
        );
    }
}