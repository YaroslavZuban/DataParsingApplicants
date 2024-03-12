package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EmployeesExperience;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.EmployeesExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<EmployeesExperience> processEmployeesExperienceInformation(Map<String, List<String>> resume) {
        if (resume.get("Должность") == null) {
            return null;
        }

        List<String> postList = resume.get("Должность");
        List<String> responsibilitiesList = resume.get("Обязанности");
        List<String> companyList = resume.get("Компания");

        List<EmployeesExperience> experienceList = new ArrayList<>(postList.size());

        for (int i = 0; i < postList.size(); i++) {
            String post = postList.get(i);
            String responsibilities = responsibilitiesList.get(i);
            String company = companyList.get(i);

            EmployeesExperience employeesExperience = find(post, responsibilities, company);

            if (employeesExperience == null) {
                employeesExperience = new EmployeesExperience(post, responsibilities, company);
                save(employeesExperience);
            }

            experienceList.add(employeesExperience);
        }

        return experienceList;
    }
}