package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.LicenceCategory;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.LicenceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseCategoryServiceImpl implements LicenseCategoryService {
    private final LicenceCategoryRepository licenceCategoryRepository;

    @Autowired
    public LicenseCategoryServiceImpl(LicenceCategoryRepository licenceCategoryRepository) {
        this.licenceCategoryRepository = licenceCategoryRepository;
    }

    @Override
    public void save(LicenceCategory newLicenceCategory) {
        if (licenceCategoryRepository.existsByCategory(newLicenceCategory.getCategory())) {
            return;
        }

        licenceCategoryRepository.save(newLicenceCategory);
    }

    public LicenceCategory findCategory(String category) {
        return licenceCategoryRepository.findByCategory(category);
    }
}