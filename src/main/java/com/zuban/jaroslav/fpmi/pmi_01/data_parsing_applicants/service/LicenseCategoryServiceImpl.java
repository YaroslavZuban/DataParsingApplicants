package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.LicenceCategory;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.LicenceCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public LicenceCategory find(String category) {
        return licenceCategoryRepository.findByCategory(category);
    }

    public List<LicenceCategory> processLicenceCategoriesInformation(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Водительские права") != null) {
            line = resume.get("Водительские права").get(0);
        }

        if (line == null) {
            return null;
        }

        String cleanCategory = line.
                toUpperCase().
                substring(line.indexOf(" ") + 1, line.length()).
                replace(" ", "");

        String[] arrayCategory = cleanCategory.split(",");

        List<LicenceCategory> licenceCategories = null;

        for (int i = 0; i < arrayCategory.length; i++) {
            if (i == 0) {
                licenceCategories = new ArrayList<>();
            }

            String category = arrayCategory[i];

            LicenceCategory licenceCategory = find(category);

            if (licenceCategory == null) {
                licenceCategory = new LicenceCategory(category);
                save(licenceCategory);
            }

            licenceCategories.add(licenceCategory);
        }

        return licenceCategories;
    }
}