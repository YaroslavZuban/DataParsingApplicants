package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.job;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Container {
    @Autowired
    DataWriter dataWriter;
    private final String[] categories = {"Имя", "Проживание", "Заработная плата", "График работы", "Образование",
            "Опыт работы", "Гражданство", "Пол", "Возраст", "Должность", "Компания", "Обязанности",
            "Окончание", "Учебное заведение", "Специальность", "Командировки", "Навыки и умения",
            "Обо мне", "Водительские права", "Иностранные языки", "Курсы и тренинги", "Title"};
    @Getter
    private Map<String, List<String>> resume = new HashMap<>();

    public void completionResume(String key, String value) {
        if (resume == null) {
            resume = new HashMap<>();
        }

        for (String category : categories) {
            if (category.equals(key)) {
                List<String> elements = resume.get(key);

                if (elements == null) {
                    elements = new ArrayList<>();
                }

                elements.add(value);

                resume.put(key, elements);
            }
        }
    }

    public void save() {
        dataWriter.writeDataBase(resume);
    }

    public void clearResume() {
        resume = null;
    }
}
