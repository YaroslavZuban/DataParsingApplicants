package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
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

    @OneToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language;

    @OneToOne
    @JoinColumn(name = "level_id", referencedColumnName = "id")
    private Level level;

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
}