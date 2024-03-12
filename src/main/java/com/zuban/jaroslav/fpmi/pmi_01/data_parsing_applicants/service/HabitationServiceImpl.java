package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Habitation;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.HabitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HabitationServiceImpl implements HabitationService {
    private final HabitationRepository habitationRepository;

    @Autowired
    public HabitationServiceImpl(HabitationRepository habitationRepository) {
        this.habitationRepository = habitationRepository;
    }

    @Override
    public void save(Habitation newHabitation) {
        if (isExist(newHabitation)) {
            return;
        }

        habitationRepository.save(newHabitation);
    }

    @Override
    public boolean isExist(Habitation habitation) {
        return habitationRepository.existsByCity(habitation.getCity());
    }

    public Habitation find(String city) {
        return habitationRepository.findByCity(city);
    }
}