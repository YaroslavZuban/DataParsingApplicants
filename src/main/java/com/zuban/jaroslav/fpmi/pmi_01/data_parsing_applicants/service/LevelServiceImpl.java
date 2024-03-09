package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Level;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void save(Level level) {
        if (!levelRepository.existsByKnowledgeLevel(level.getKnowledgeLevel())) {
            return;
        }

        levelRepository.save(level);
    }
}