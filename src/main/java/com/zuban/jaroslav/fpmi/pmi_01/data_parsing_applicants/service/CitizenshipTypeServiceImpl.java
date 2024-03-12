package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.CitizenshipType;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.CitizenshipTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<CitizenshipType> processCitizenshipTypesInformation(Map<String, List<String>> resume) {
        List<String> citizenshipList = resume.get("Гражданство");

        if (citizenshipList == null) {
            return null;
        }

        String citizenship = citizenshipList.get(0);

        String[] elements = citizenship.split(" / ");
        List<CitizenshipType> citizenshipTypeList = new ArrayList<>(elements.length);

        for (String element : elements) {
            CitizenshipType citizenshipType = find(element);

            if (citizenshipType == null) {
                citizenshipType = new CitizenshipType(element);
                save(citizenshipType);
            }

            citizenshipTypeList.add(citizenshipType);
        }

        return citizenshipTypeList;
    }
}