package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.EducationType;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Specification;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SpecificationServiceImpl implements SpecificationService {
    private final SpecificationRepository specificationRepository;
    private final EducationTypeServiceImpl educationTypeService;

    @Autowired
    public SpecificationServiceImpl(SpecificationRepository specificationRepository,
                                    EducationTypeServiceImpl educationTypeService) {
        this.specificationRepository = specificationRepository;
        this.educationTypeService = educationTypeService;
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

    public List<Specification> processSpecificationInformation(Map<String, List<String>> resume) {
        List<EducationType> educationTypeList = educationTypeService.processEducationTypeInformation(resume);

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

            Specification specification = find(year, educationalInstitution, direction, educationType.getId());

            if (specification == null) {
                specification = new Specification(year, educationalInstitution, direction, educationType);
                save(specification);
            }

            specifications.add(specification);
        }

        return specifications;
    }
}
