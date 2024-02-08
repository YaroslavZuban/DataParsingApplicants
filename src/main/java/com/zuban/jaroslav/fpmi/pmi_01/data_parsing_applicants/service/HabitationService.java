package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Habitation;
import org.springframework.stereotype.Service;

@Service
public interface HabitationService {
    void save(Habitation newHabitation);

    boolean isExist(Habitation habitation);
}
