package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.LanguageForeign;
import org.springframework.stereotype.Service;

@Service
public interface LanguageForeignService {
    void save(LanguageForeign newLanguageForeign);

    boolean isExist(LanguageForeign languageForeign);
}
