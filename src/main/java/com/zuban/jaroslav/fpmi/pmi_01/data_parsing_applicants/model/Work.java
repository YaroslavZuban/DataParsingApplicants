package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "work")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private PersonalData personalData;

    private EmployeesExperience employeesExperience;

    public Work() {
    }

    public Work(PersonalData personalData, EmployeesExperience employeesExperience) {
        this.personalData = personalData;
        this.employeesExperience = employeesExperience;
    }

    public int getId() {
        return id;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public EmployeesExperience getEmployeesExperience() {
        return employeesExperience;
    }

    public void setEmployeesExperience(EmployeesExperience employeesExperience) {
        this.employeesExperience = employeesExperience;
    }
}
