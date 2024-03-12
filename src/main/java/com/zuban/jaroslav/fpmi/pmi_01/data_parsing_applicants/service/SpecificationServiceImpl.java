package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Specification;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    private final SpecificationRepository specificationRepository;

    @Autowired
    public SpecificationServiceImpl(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @Override
    public void save(Specification newSpecification) {
        if (isExist(newSpecification)) {
            return;
        }

        specificationRepository.save(newSpecification);
    }

    @Override
    public boolean isExist(Specification specification) {
        return specificationRepository.existsBySpecifications(
                specification.getEnding(),
                specification.getEducationalInstitution(),
                specification.getDirection(),
                specification.getEducationType().getId());
    }

    public Specification find(
            int ending,
            String educationalInstitution,
            String direction,
            int educationTypeId) {
        return specificationRepository.findMatchingSpecifications(ending, educationalInstitution, direction, educationTypeId);
    }
}
