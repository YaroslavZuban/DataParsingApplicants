package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.BusinessTrips;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.BusinessTripsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessTripsServiceImpl implements BusinessTripsService {
    private final BusinessTripsRepository businessTripsRepository;

    @Autowired
    public BusinessTripsServiceImpl(BusinessTripsRepository businessTripsRepository) {
        this.businessTripsRepository = businessTripsRepository;
    }

    @Override
    public void save(BusinessTrips newBusinessTrips) {
        if (!businessTripsRepository.existsByReadiness(newBusinessTrips.getReadiness())) {
            return;
        }

        businessTripsRepository.save(newBusinessTrips);
    }

    public BusinessTrips findBusinessTrips(String readiness) {
        return businessTripsRepository.findByReadiness(readiness);
    }
}