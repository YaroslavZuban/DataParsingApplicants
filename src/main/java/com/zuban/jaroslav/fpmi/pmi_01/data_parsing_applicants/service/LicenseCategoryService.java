package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.LicenceCategory;
import org.springframework.stereotype.Service;

@Service
public interface LicenseCategoryService {
    void save(LicenceCategory newLicenceCategory);

    boolean isExist(LicenceCategory licenceCategory);
}
