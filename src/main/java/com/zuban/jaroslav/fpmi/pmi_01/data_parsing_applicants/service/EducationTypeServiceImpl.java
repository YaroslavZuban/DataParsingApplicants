package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EducationType;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.EducationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}