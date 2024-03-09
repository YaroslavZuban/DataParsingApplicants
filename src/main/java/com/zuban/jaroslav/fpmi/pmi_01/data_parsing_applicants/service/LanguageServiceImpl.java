package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Language;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public void save(Language newLanguage) {
        if (!languageRepository.existsByName(newLanguage.getName())) {
            return;
        }

        languageRepository.save(newLanguage);
    }

    public Language findLanguage(String name) {
        return languageRepository.findByName(name);
    }
}