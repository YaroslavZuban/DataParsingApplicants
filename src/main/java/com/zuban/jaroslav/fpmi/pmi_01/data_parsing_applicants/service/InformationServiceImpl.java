package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class InformationServiceImpl implements InformationService {
    private final InformationRepository informationRepository;
    private final LanguageServiceImpl languageService;
    private final LevelServiceImpl levelService;
    private final LicenseCategoryServiceImpl licenseCategoryService;
    private final BusinessTripsServiceImpl businessTripsService;

    @Autowired
    public InformationServiceImpl(InformationRepository informationRepository,
                                  LanguageServiceImpl languageService,
                                  LevelServiceImpl levelService,
                                  LicenseCategoryServiceImpl licenseCategoryService,
                                  BusinessTripsServiceImpl businessTripsService) {
        this.informationRepository = informationRepository;
        this.languageService = languageService;
        this.levelService = levelService;
        this.licenseCategoryService = licenseCategoryService;
        this.businessTripsService = businessTripsService;
    }

    @Override
    public void save(Information newInformation) {
        informationRepository.save(newInformation);
    }

    @Override
    public boolean isExist(Information information) {
        return informationRepository.
                existsByInformation(
                        information.getCourses(),
                        information.getSkills(),
                        information.getAboutMe(),
                        information.getBusinessTrips().getId()
                );
    }

    public Information processInformation(Map<String, List<String>> resume) {
        Information information = processNewInformation(resume);

        //Уровень языка и сам язык какой он есть. Данные списки используются вместе.
        List<Language> languages = languageService.processLanguageInformation(resume);
        List<Level> levels = levelService.processLevelInformation(resume);

        BusinessTrips businessTrips = businessTripsService.processBusinessTripsInformation(resume);

        //Если в дополнительной информации появились еще языки
        List<Language> languageList = information.getLanguages();
        List<Level> levelList = information.getLevels();

        if (languages != null && levels != null) {
            if (information.getLanguages() == null) {
                languageList = new ArrayList<>(8);
            }

            if (information.getLevels() == null) {
                levelList = new ArrayList<>(8);
            }

            for (int i = 0; i < languages.size(); i++) {
                languageList.add(languages.get(i));
                levelList.add(levels.get(i));
            }
        }

        List<LicenceCategory> licenceCategoryList = licenseCategoryService.processLicenceCategoriesInformation(resume);
        List<LicenceCategory> licenceCategories = information.getCategoryList();

        if (licenceCategoryList != null) {
            for (LicenceCategory licenceCategory : licenceCategoryList) {
                if (licenceCategories == null) {
                    licenceCategories = new ArrayList<>(8);
                }

                licenceCategories.add(licenceCategory);
            }

            information.setCategoryList(licenceCategories);
        }

        information.setLanguages(languageList);
        information.setLevels(levelList);
        information.setBusinessTrips(businessTrips);

        if (businessTrips != null) {
            businessTripsService.save(businessTrips);
        }

        save(information);

        return information;
    }

    private Information processNewInformation(Map<String, List<String>> resume) {
        String courses = null;

        if (resume.get("Курсы и тренинги") != null) {
            courses = resume.get("Курсы и тренинги").get(0);
        }

        String skills = null;

        if (resume.get("Навыки и умения") != null) {
            skills = resume.get("Навыки и умения").get(0);
        }

        String aboutMe = null;

        if (resume.get("Обо мне") != null) {
            skills = resume.get("Обо мне").get(0);
        }

        return new Information(courses, skills, aboutMe);
    }
}