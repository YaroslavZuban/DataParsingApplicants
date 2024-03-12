package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.CitizenshipType;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.CitizenshipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitizenshipTypeServiceImpl implements CitizenshipTypeService {
    private final CitizenshipTypeRepository citizenshipTypeRepository;

    @Autowired
    public CitizenshipTypeServiceImpl(CitizenshipTypeRepository citizenshipTypeRepository) {
        this.citizenshipTypeRepository = citizenshipTypeRepository;
    }

    @Override
    public void save(CitizenshipType newCitizenshipType) {
        if (isExist(newCitizenshipType)) {
            return;
        }

        citizenshipTypeRepository.save(newCitizenshipType);
    }

    @Override
    public boolean isExist(CitizenshipType citizenshipType) {
        return citizenshipTypeRepository.existsByName(citizenshipType.getName());
    }

    public CitizenshipType find(String name) {
        return citizenshipTypeRepository.findByName(name);
    }
}