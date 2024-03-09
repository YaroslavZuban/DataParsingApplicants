package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.BusinessTrips;
import org.springframework.stereotype.Service;

@Service
public interface BusinessTripsService {
    void save(BusinessTrips newBusinessTrips);
}
