package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Gender;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public void save(Gender newGender) {
        if (isExist(newGender)) {
            return;
        }

        genderRepository.save(newGender);
    }

    @Override
    public boolean isExist(Gender gender) {
        return genderRepository.existsByType(gender.getType());
    }

    public Gender find(String type) {
        return genderRepository.findByType(type);
    }

    public Gender processGenderInformation(Map<String, List<String>> resume) {
        if (resume.get("Пол") == null) {
            return null;
        }

        String type = resume.get("Пол").get(0);

        Gender gender = find(type);

        if (gender == null) {
            gender = new Gender(type);
            save(gender);
        }

        return gender;
    }
}
