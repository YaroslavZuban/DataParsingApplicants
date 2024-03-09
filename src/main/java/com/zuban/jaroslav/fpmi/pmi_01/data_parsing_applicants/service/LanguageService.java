package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Language;
import org.springframework.stereotype.Service;

@Service
public interface LanguageService {
    void save(Language newLanguage);
}
