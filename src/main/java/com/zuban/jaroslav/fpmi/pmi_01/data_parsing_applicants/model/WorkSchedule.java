package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "work_schedule")
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "work_type")
    private String workType;

    @ManyToMany(mappedBy = "workSchedule")
    private List<PersonalData> personalData;

    public WorkSchedule() {
    }

    public WorkSchedule(String workType) {
        this.workType = workType;
    }

    public int getId() {
        return id;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
