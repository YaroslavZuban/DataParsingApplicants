package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.WorkSchedule;
import org.springframework.stereotype.Service;

@Service
public interface WorkScheduleService {
    void save(WorkSchedule newWorkSchedule);

    boolean isExist(WorkSchedule workSchedule);
}
