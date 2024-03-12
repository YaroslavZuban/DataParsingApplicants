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
    private final GenderServiceImpl genderServer;

    private final WorkScheduleServiceImpl workScheduleService;

    private final HabitationServiceImpl habitationService;

    private final EmployeesExperienceServiceImpl employeesExperienceService;

    @Autowired
    public DataWriter(LanguageServiceImpl languageService, LevelServiceImpl levelService,
                      BusinessTripsServiceImpl businessTripsService,
                      LicenseCategoryServiceImpl licenseCategoryService,
                      InformationServiceImpl informationService,
                      EducationTypeServiceImpl educationTypeService,
                      CitizenshipTypeServiceImpl citizenshipTypeService,
                      SpecificationServiceImpl specificationService, GenderServiceImpl genderServer, WorkScheduleServiceImpl workScheduleService, HabitationServiceImpl habitationService, EmployeesExperienceServiceImpl employeesExperienceService) {
        this.languageService = languageService;
        this.levelService = levelService;
        this.businessTripsService = businessTripsService;
        this.licenseCategoryService = licenseCategoryService;
        this.informationService = informationService;
        this.educationTypeService = educationTypeService;
        this.citizenshipTypeService = citizenshipTypeService;
        this.specificationService = specificationService;
        this.genderServer = genderServer;
        this.workScheduleService = workScheduleService;
        this.habitationService = habitationService;
        this.employeesExperienceService = employeesExperienceService;
    }

    public void writeDataBase(Map<String, List<String>> resume) {
        //тестирование
        //processEmployeesExperienceInformation(resume);
        //processHabitationInformation(resume);
        //processWorkScheduleInformation(resume);
        //processGenderInformation(resume);
        //addCitizenshipTypes(resume);
        //addSpecification(resume);
        //addGender(resume);
        //newInformation(resume);
    }

    private List<EmployeesExperience> processEmployeesExperienceInformation(Map<String, List<String>> resume) {
        if (resume.get("Должность") == null) {
            return null;
        }

        List<String> postList = resume.get("Должность");
        List<String> responsibilitiesList = resume.get("Обязанности");
        List<String> companyList = resume.get("Компания");

        List<EmployeesExperience> experienceList = new ArrayList<>(postList.size());

        for (int i = 0; i < postList.size(); i++) {
            String post = postList.get(i);
            String responsibilities = responsibilitiesList.get(i);
            String company = companyList.get(i);

            EmployeesExperience employeesExperience = employeesExperienceService.find(
                    post,
                    responsibilities,
                    company
            );

            if (employeesExperience == null) {
                employeesExperience = new EmployeesExperience(post, responsibilities, company);
                employeesExperienceService.save(employeesExperience);
            }

            experienceList.add(employeesExperience);
        }

        return experienceList;
    }

    private Habitation processHabitationInformation(Map<String, List<String>> resume) {
        if (resume.get("Проживание") == null) {
            return null;
        }

        String city = resume.get("Проживание").get(0);

        if (city.contains(",")) {
            city = city.substring(0, city.indexOf(","));
        }

        Habitation habitation = habitationService.find(city);

        if (habitation == null) {
            habitation = new Habitation(city);
            habitationService.save(habitation);
        }

        return habitation;
    }

    private List<WorkSchedule> processWorkScheduleInformation(Map<String, List<String>> resume) {
        if (resume.get("График работы") == null) {
            return null;
        }

        String workType = resume.get("График работы").get(0);
        String[] elements = workType.split(" / ");

        List<WorkSchedule> workScheduleList = new ArrayList<>(elements.length);

        for (String element : elements) {
            WorkSchedule workSchedule = workScheduleService.find(element);

            if (workSchedule == null) {
                workSchedule = new WorkSchedule(element);
                workScheduleService.save(workSchedule);
            }

            workScheduleList.add(workSchedule);
        }

        return workScheduleList;
    }

    //Гендер
    private Gender processGenderInformation(Map<String, List<String>> resume) {
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
    private List<CitizenshipType> processCitizenshipTypesInformation(Map<String, List<String>> resume) {
        List<String> citizenshipList = resume.get("Гражданство");

        if (citizenshipList == null) {
            return null;
        }

        String citizenship = citizenshipList.get(0);

        String[] elements = citizenship.split(" / ");
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
    private List<Specification> processSpecificationInformation(Map<String, List<String>> resume) {
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
    private Information processInformation(Map<String, List<String>> resume) {
        Information information = processNewInformation(resume);

        //Уровень языка и сам язык какой он есть. Данные списки используются вместе.
        List<Language> languages = processLanguageInformation(resume);
        List<Level> levels = processLevelInformation(resume);

        BusinessTrips businessTrips = processBusinessTripsInformation(resume);

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

        List<LicenceCategory> licenceCategoryList = processLicenceCategoriesInformation(resume);
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

    private List<Language> processLanguageInformation(Map<String, List<String>> resume) {
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

    private List<Level> processLevelInformation(Map<String, List<String>> resume) {
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

    private BusinessTrips processBusinessTripsInformation(Map<String, List<String>> resume) {
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

    private List<LicenceCategory> processLicenceCategoriesInformation(Map<String, List<String>> resume) {
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