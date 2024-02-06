package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employment_type")
public class EmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private PersonalData personalData;

    private WorkSchedule workSchedule;

    public EmploymentType() {
    }

    public EmploymentType(PersonalData personalData, WorkSchedule workSchedule) {
        this.personalData = personalData;
        this.workSchedule = workSchedule;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public int getId() {
        return id;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public WorkSchedule getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(WorkSchedule workSchedule) {
        this.workSchedule = workSchedule;
    }
}
