package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "add_information")
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "courses_and_trainings")
    private String courses;

    @Column(name = "skills")
    private String skills;

    @Column(name = "about_me")
    private String aboutMe;

    @OneToMany
    @JoinColumn(name = "language_foreign_id", referencedColumnName = "id")
    private List<LanguageForeign> languageForeign;

    @OneToOne
    @JoinColumn(name = "business_trips_id", referencedColumnName = "id")
    private BusinessTrips businessTrips;

    @OneToMany
    @JoinTable(name = "license",
            joinColumns = @JoinColumn(name = "add_information_id"),
            inverseJoinColumns = @JoinColumn(name = "license_category_id"))
    private List<LicenceCategory> categoryList;

    @OneToOne(mappedBy = "information")
    private PersonalData personalData;

    public Information() {
    }

    public int getId() {
        return id;
    }

    public List<LanguageForeign> getLanguageForeign() {
        return languageForeign;
    }

    public void setLanguageForeign(List<LanguageForeign> languageForeign) {
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