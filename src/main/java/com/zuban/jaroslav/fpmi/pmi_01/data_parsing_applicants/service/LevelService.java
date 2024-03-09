package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Level;
import org.springframework.stereotype.Service;

@Service
public interface LevelService {
    void save(Level level);
}
