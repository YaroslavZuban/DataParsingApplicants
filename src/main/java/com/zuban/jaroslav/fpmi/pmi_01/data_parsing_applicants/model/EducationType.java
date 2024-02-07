package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "education_type")
public class EducationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "education_level")
    private String educationLevel;

    @OneToOne(mappedBy = "educationType")
    private Specification specification;

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
