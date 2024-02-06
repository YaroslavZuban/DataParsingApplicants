package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "")
public class EducationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "education_level")
    private String educationLevel;

    public EducationType() {
    }

    public EducationType(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    public int getId() {
        return id;
    }

    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }
}