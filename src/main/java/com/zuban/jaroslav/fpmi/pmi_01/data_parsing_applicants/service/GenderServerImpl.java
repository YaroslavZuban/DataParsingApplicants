package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Gender;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderServerImpl implements GenderService {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderServerImpl(GenderRepository genderRepository) {
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
}
