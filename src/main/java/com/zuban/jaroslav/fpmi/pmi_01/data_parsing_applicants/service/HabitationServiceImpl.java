package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Habitation;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.HabitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Habitation processHabitationInformation(Map<String, List<String>> resume) {
        if (resume.get("Проживание") == null) {
            return null;
        }

        String city = resume.get("Проживание").get(0);

        if (city.contains(",")) {
            city = city.substring(0, city.indexOf(","));
        }

        Habitation habitation = find(city);

        if (habitation == null) {
            habitation = new Habitation(city);
            save(habitation);
        }

        return habitation;
    }
}