package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Language;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void save(Language newLanguage) {
        if (languageRepository.existsByName(newLanguage.getName())) {
            return;
        }

        languageRepository.save(newLanguage);
    }

    public Language findLanguage(String name) {
        return languageRepository.findByName(name);
    }

    public List<Language> processLanguageInformation(Map<String, List<String>> resume) {
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

            Language language = findLanguage(languageLine);

            if (language == null) {
                language = new Language(languageLine);
                save(language);
            }

            languages.add(language);
        }

        return languages;
    }
}