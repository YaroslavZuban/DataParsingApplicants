package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "citizenship_type")
public class CitizenshipType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "citizenshipType")
    private List<PersonalData> personalDataList;

    public CitizenshipType() {
    }

    public CitizenshipType(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}