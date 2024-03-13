package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Level;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void save(Level level) {
        if (levelRepository.existsByKnowledgeLevel(level.getKnowledgeLevel())) {
            return;
        }

        levelRepository.save(level);
    }

    public Level findLevel(String level) {
        return levelRepository.findByKnowledgeLevel(level);
    }

    public List<Level> processLevelInformation(Map<String, List<String>> resume) {
        String line = null;

        if (resume.get("Иностранные языки") != null) {
            line = resume.get("Иностранные языки").get(0);
        }

        if (line == null) {
            return null;
        }

        String[] arrLanguage = line.split(",");

        List<Level> levels = null;

        for (int i = 0; i < arrLanguage.length; i++) {
            String levelLine = arrLanguage[i];

            if (i == 0) {
                levels = new ArrayList<>();
            }

            int startIndex = levelLine.indexOf("(") + 1;
            int endIndex = levelLine.indexOf(")");

            levelLine = levelLine.substring(startIndex, endIndex);

            Level level = findLevel(levelLine);

            if (level == null) {
                level = new Level(levelLine);
                save(level);
            }

            levels.add(level);
        }

        return levels;
    }
}
