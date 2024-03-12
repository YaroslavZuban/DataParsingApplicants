package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataWriter {
    private final LanguageServiceImpl languageService;
    private final LevelServiceImpl levelService;
    private final BusinessTripsServiceImpl businessTripsService;
    private final LicenseCategoryServiceImpl licenseCategoryService;
    private final InformationServiceImpl informationService;

    @Autowired
    public DataWriter(LanguageServiceImpl languageService, LevelServiceImpl levelService,
                      BusinessTripsServiceImpl businessTripsService,
                      LicenseCategoryServiceImpl licenseCategoryService,
                      InformationServiceImpl informationService) {
        this.languageService = languageService;
        this.levelService = levelService;
        this.businessTripsService = businessTripsService;
        this.licenseCategoryService = licenseCategoryService;
        this.informationService = informationService;
    }

    public void writeDataBase(Map<String, List<String>> resume) {
        newInformation(resume);
    }

    private Information newInformation(Map<String, List<String>> resume) {
        Information information = addInformation(resume);

        //Уровень языка и сам язык какой он есть. Данные списки используются вместе.
        List<Language> languages = addLanguage(resume);
        List<Level> levels = addLevel(resume);

        BusinessTrips businessTrips = addBusinessTrips(resume);

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

        List<LicenceCategory> licenceCategoryList = licenceCategories(resume);
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
            businessTrips.setInformation(information);
            businessTripsService.save(businessTrips);
        }

        informationService.save(information);

        System.out.println(information);

        return information;
    }

    private List<Language> addLanguage(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Иностранные языки") != null) {
            line = resume.get("Иностранные языки").get(0);
        }

        if (line == null) {
            return null;
        }

        String[] arrLanguage = line.split(",");

        List<Language> languages = null;

        for (int i = 0; i < arrLanguage.length; i++) {
            String languageLine = arrLanguage[i];

            if (i == 0) {
                languages = new ArrayList<>();
            }

            languageLine = languageLine.substring(0, languageLine.indexOf(" "));

            Language language = languageService.findLanguage(languageLine);

            if (language == null) {
                language = new Language(languageLine);
                languageService.save(language);
            }

            languages.add(language);
        }

        return languages;
    }

    private List<Level> addLevel(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Иностранные языки") != null) {
            line = resume.get("Иностранные языки").get(0);
        }

        if (line == null) {
            return null;
        }

        String[] arrLanguage = line.split(",");

        List<Level> levels = null;

        for (int i = 0; i < arrLanguage.length; i++) {
            String levelLine = arrLanguage[i];

            if (i == 0) {
                levels = new ArrayList<>();
            }

            int startIndex = line.indexOf("(") + 1;
            int endIndex = line.indexOf(")");

            levelLine = levelLine.substring(startIndex, endIndex);

            Level level = levelService.findLevel(levelLine);

            if (level == null) {
                level = new Level(levelLine);
                levelService.save(level);
            }

            levels.add(level);
        }

        return levels;
    }

    private BusinessTrips addBusinessTrips(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Командировки") != null) {
            line = resume.get("Командировки").get(0);
        }

        if (line == null) {
            return null;
        }

        BusinessTrips businessTrips = businessTripsService.findBusinessTrips(line);

        if (businessTrips == null) {
            businessTrips = new BusinessTrips(line);
            businessTripsService.save(businessTrips);
        }

        return businessTrips;
    }

    private List<LicenceCategory> licenceCategories(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Водительские права") != null) {
            line = resume.get("Водительские права").get(0);
        }

        if (line == null) {
            return null;
        }

        String cleanCategory = line.replace("Категория ", "").replace(" ", "");

        String[] arrayCategory = cleanCategory.split(",");

        List<LicenceCategory> licenceCategories = null;

        for (int i = 0; i < arrayCategory.length; i++) {
            if (i == 0) {
                licenceCategories = new ArrayList<>();
            }

            String category = arrayCategory[i];

            LicenceCategory licenceCategory = licenseCategoryService.findCategory(category);

            if (licenceCategory == null) {
                licenceCategory = new LicenceCategory(category);
                licenseCategoryService.save(licenceCategory);
            }

            licenceCategories.add(licenceCategory);
        }

        return licenceCategories;
    }

    private Information addInformation(Map<String, List<String>> resume) {
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