package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Specification;
import org.springframework.stereotype.Service;

@Service
public interface SpecificationService {
    void save(Specification newSpecification);

    boolean isExist(Specification specification);
}
