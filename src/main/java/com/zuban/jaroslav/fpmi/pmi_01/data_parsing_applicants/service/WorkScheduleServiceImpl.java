package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.service;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.WorkSchedule;
import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService {
    private final WorkScheduleRepository workScheduleRepository;

    @Autowired
    public WorkScheduleServiceImpl(WorkScheduleRepository workScheduleRepository) {
        this.workScheduleRepository = workScheduleRepository;
    }

    @Override
    public void save(WorkSchedule newWorkSchedule) {
        if (isExist(newWorkSchedule)) {
            return;
        }

        workScheduleRepository.save(newWorkSchedule);
    }

    @Override
    public boolean isExist(WorkSchedule workSchedule) {
        return workScheduleRepository.existsByWorkType(workSchedule.getWorkType());
    }

    public WorkSchedule find(String workType) {
        return workScheduleRepository.findByWorkType(workType);
    }

    public List<WorkSchedule> processWorkScheduleInformation(Map<String, List<String>> resume) {
        if (resume.get("График работы") == null) {
            return null;
        }

        String workType = resume.get("График работы").get(0);
        String[] elements = workType.split(" / ");

        List<WorkSchedule> workScheduleList = new ArrayList<>(elements.length);

        for (String element : elements) {
            WorkSchedule workSchedule = find(element);

            if (workSchedule == null) {
                workSchedule = new WorkSchedule(element);
                save(workSchedule);
            }

            workScheduleList.add(workSchedule);
        }

        return workScheduleList;
    }
}