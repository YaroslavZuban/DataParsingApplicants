package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "specification")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private EducationType educationType;

    @Column(name = "ending")
    private int ending;

    @Column(name = "educational_institution")
    private String educational_institution;

    @Column(name = "direction")
    private String direction;

    public Specification() {
    }

    public Specification(EducationType educationType, int ending, String educational_institution, String direction) {
        this.educationType = educationType;
        this.ending = ending;
        this.educational_institution = educational_institution;
        this.direction = direction;
    }

    public int getId() {
        return id;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    public int getEnding() {
        return ending;
    }

    public void setEnding(int ending) {
        this.ending = ending;
    }

    public String getEducational_institution() {
        return educational_institution;
    }

    public void setEducational_institution(String educational_institution) {
        this.educational_institution = educational_institution;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
