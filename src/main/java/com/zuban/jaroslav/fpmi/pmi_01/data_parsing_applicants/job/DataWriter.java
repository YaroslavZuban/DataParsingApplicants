package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataWriter {
    private final LanguageServiceImpl languageService;
    private final LevelServiceImpl levelService;
    private final BusinessTripsServiceImpl businessTripsService;
    private final LicenseCategoryServiceImpl licenseCategoryService;
    private final InformationServiceImpl informationService;
    private final EducationTypeServiceImpl educationTypeService;
    private final CitizenshipTypeServiceImpl citizenshipTypeService;
    private final SpecificationServiceImpl specificationService;
    private final GenderServerImpl genderServer;

    @Autowired
    public DataWriter(LanguageServiceImpl languageService, LevelServiceImpl levelService,
                      BusinessTripsServiceImpl businessTripsService,
                      LicenseCategoryServiceImpl licenseCategoryService,
                      InformationServiceImpl informationService,
                      EducationTypeServiceImpl educationTypeService,
                      CitizenshipTypeServiceImpl citizenshipTypeService,
                      SpecificationServiceImpl specificationService, GenderServerImpl genderServer) {
        this.languageService = languageService;
        this.levelService = levelService;
        this.businessTripsService = businessTripsService;
        this.licenseCategoryService = licenseCategoryService;
        this.informationService = informationService;
        this.educationTypeService = educationTypeService;
        this.citizenshipTypeService = citizenshipTypeService;
        this.specificationService = specificationService;
        this.genderServer = genderServer;
    }

    public void writeDataBase(Map<String, List<String>> resume) {
        //тестирование
        //addCitizenshipTypes(resume);
        //addSpecification(resume);
        //addGender(resume);
        //newInformation(resume);
    }

    //Гендер
    private Gender addGender(Map<String, List<String>> resume) {
        if (resume.get("Пол") == null) {
            return null;
        }

        String type = resume.get("Пол").get(0);

        Gender gender = genderServer.find(type);

        if (gender == null) {
            gender = new Gender(type);
            genderServer.save(gender);
        }

        return gender;
    }

    //Тип гражданства
    private List<CitizenshipType> addCitizenshipTypes(Map<String, List<String>> resume) {
        List<String> citizenshipList = resume.get("Гражданство");

        if (citizenshipList == null) {
            return null;
        }

        String citizenship = citizenshipList.get(0);

        String[] elements = citizenship.split("/");
        List<CitizenshipType> citizenshipTypeList = new ArrayList<>(elements.length);

        for (String element : elements) {
            CitizenshipType citizenshipType = citizenshipTypeService.find(element);

            if (citizenshipType == null) {
                citizenshipType = new CitizenshipType(element);
                citizenshipTypeService.save(citizenshipType);
            }

            citizenshipTypeList.add(citizenshipType);
        }

        return citizenshipTypeList;
    }

    //Спецификация
    private List<Specification> addSpecification(Map<String, List<String>> resume) {
        List<String> educationTypesString = resume.get("Образование");

        if (educationTypesString == null) {
            return null;
        }

        List<EducationType> educationTypeList = new ArrayList<>(educationTypesString.size());

        //С еденицы начинается потому, что мы не будем учитывать первую строчку при описании резюме
        for (int i = 1; i < educationTypesString.size(); i++) {
            String educationLevel = educationTypesString.get(i);
            EducationType educationType = educationTypeService.find(educationLevel);

            if (educationType == null) {
                educationType = new EducationType(educationLevel);
                educationTypeService.save(educationType);
            }

            educationTypeList.add(educationType);
        }

        List<String> endings = resume.get("Окончание");
        List<String> educationalInstitutions = resume.get("Учебное заведение");
        List<String> directions = resume.get("Специальность");

        Pattern pattern = Pattern.compile("\\b\\d+\\b");

        List<Specification> specifications = new ArrayList<>();

        for (int i = 0; i < educationTypeList.size(); i++) {
            String ending = endings.get(i);
            String educationalInstitution = educationalInstitutions.get(i);
            String direction = directions.get(i);
            EducationType educationType = educationTypeList.get(i);

            Matcher matcher = pattern.matcher(ending);
            int year = 0;

            if (matcher.find()) {
                String yearString = matcher.group();

                // Преобразование строки в число (год)
                year = Integer.parseInt(yearString);
            }

            Specification specification = specificationService.find(year, educationalInstitution, direction, educationType.getId());

            if (specification == null) {
                specification = new Specification(year, educationalInstitution, direction, educationType);
                specificationService.save(specification);
            }

            specifications.add(specification);
        }

        return specifications;
    }

    // Работа шла над дополнительной информацией
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