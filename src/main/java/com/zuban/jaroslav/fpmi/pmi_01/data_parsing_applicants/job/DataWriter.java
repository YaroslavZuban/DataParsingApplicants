package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.*;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataWriter {
    private LanguageServiceImpl languageService;
    private LevelServiceImpl levelService;
    private BusinessTripsServiceImpl businessTripsService;

    @Autowired
    public DataWriter(LanguageServiceImpl languageService, LevelServiceImpl levelService, BusinessTripsServiceImpl businessTripsService) {
        this.languageService = languageService;
        this.levelService = levelService;
        this.businessTripsService = businessTripsService;
    }

    public DataWriter() {
    }


    public void writeDataBase(Map<String, List<String>> resume) {

    }

    private Information information(Map<String, List<String>> resume) {
        Information information = new Information();

        Language language = language(resume);
        Level level = level(resume);
        BusinessTrips businessTrips = businessTrips(resume);


    }

    private Language language(Map<String, List<String>> resume) {
        String line = resume.get("Иностранные языки").get(0);

        if (line == null) {
            return null;
        }

        line = line.substring(0, line.indexOf(" "));

        Language language = languageService.findLanguage(line);

        if (language == null) {
            language = new Language(line);
            languageService.save(language);
        }

        return language;
    }

    private Level level(Map<String, List<String>> resume) {
        String line = resume.get("Иностранные языки").get(0);

        if (line == null) {
            return null;
        }

        int startIndex = line.indexOf("(") + 1;
        int endIndex = line.indexOf(")");

        line = line.substring(startIndex, endIndex);

        Level level = levelService.findLevel(line);

        if (level == null) {
            level = new Level(line);
            levelService.save(level);
        }

        return level;
    }

    private BusinessTrips businessTrips(Map<String, List<String>> resume) {
        String line = resume.get("Командировки").get(0);

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

    }
}
