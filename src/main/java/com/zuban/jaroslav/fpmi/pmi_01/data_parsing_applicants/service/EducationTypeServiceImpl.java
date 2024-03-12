package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EducationType;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.EducationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EducationTypeServiceImpl implements EducationTypeService {
    private final EducationTypeRepository educationTypeRepository;

    @Autowired
    public EducationTypeServiceImpl(EducationTypeRepository educationTypeRepository) {
        this.educationTypeRepository = educationTypeRepository;
    }

    @Override
    public void save(EducationType newEducationType) {
        if (isExist(newEducationType)) {
            return;
        }

        educationTypeRepository.save(newEducationType);
    }

    @Override
    public boolean isExist(EducationType educationType) {
        return educationTypeRepository.existsByEducationLevel(educationType.getEducationLevel());
    }

    public EducationType find(String educationLevel) {
        return educationTypeRepository.findByEducationLevel(educationLevel);
    }

    public List<EducationType> processEducationTypeInformation(Map<String, List<String>> resume) {
        List<String> educationTypesString = resume.get("Образование");

        if (educationTypesString == null) {
            return null;
        }

        List<EducationType> educationTypeList = new ArrayList<>(educationTypesString.size());

        //С еденицы начинается потому, что мы не будем учитывать первую строчку при описании резюме
        for (int i = 1; i < educationTypesString.size(); i++) {
            String educationLevel = educationTypesString.get(i);
            EducationType educationType = find(educationLevel);

            if (educationType == null) {
                educationType = new EducationType(educationLevel);
                save(educationType);
            }

            educationTypeList.add(educationType);
        }

        return educationTypeList;
    }
}