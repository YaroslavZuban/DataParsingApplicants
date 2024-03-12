package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.BusinessTrips;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.BusinessTripsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BusinessTripsServiceImpl implements BusinessTripsService {
    private final BusinessTripsRepository businessTripsRepository;

    @Autowired
    public BusinessTripsServiceImpl(BusinessTripsRepository businessTripsRepository) {
        this.businessTripsRepository = businessTripsRepository;
    }

    @Override
    public void save(BusinessTrips newBusinessTrips) {
        if (businessTripsRepository.existsByReadiness(newBusinessTrips.getReadiness())) {
            return;
        }

        businessTripsRepository.save(newBusinessTrips);
    }

    public BusinessTrips find(String readiness) {
        return businessTripsRepository.findByReadiness(readiness);
    }

    public BusinessTrips processBusinessTripsInformation(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Командировки") != null) {
            line = resume.get("Командировки").get(0);
        }

        if (line == null) {
            return null;
        }

        BusinessTrips businessTrips = find(line);

        if (businessTrips == null) {
            businessTrips = new BusinessTrips(line);
            save(businessTrips);
        }

        return businessTrips;
    }
}