package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "add_information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LanguageForeign languageForeign;

    private BusinessTrips businessTrips;

    @Column(name = "courses_and_trainings")
    private String courses;

    @Column(name = "skills")
    private String skills;

    @Column(name = "about_me")
    private String aboutMe;

    public Information() {
    }

    public Information(LanguageForeign languageForeign, BusinessTrips businessTrips, String courses, String skills, String aboutMe) {
        this.languageForeign = languageForeign;
        this.businessTrips = businessTrips;
        this.courses = courses;
        this.skills = skills;
        this.aboutMe = aboutMe;
    }

    public int getId() {
        return id;
    }

    public LanguageForeign getLanguageForeign() {
        return languageForeign;
    }

    public void setLanguageForeign(LanguageForeign languageForeign) {
        this.languageForeign = languageForeign;
    }

    public BusinessTrips getBusinessTrips() {
        return businessTrips;
    }

    public void setBusinessTrips(BusinessTrips businessTrips) {
        this.businessTrips = businessTrips;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}