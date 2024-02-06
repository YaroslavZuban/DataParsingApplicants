package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "citizenship")
public class Citizenship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private PersonalData personalData;

    private CitizenshipType citizenshipType;

    private Citizenship() {
    }

    public Citizenship(PersonalData personalData, CitizenshipType citizenshipType) {
        this.personalData = personalData;
        this.citizenshipType = citizenshipType;
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

    public CitizenshipType getCitizenshipType() {
        return citizenshipType;
    }

    public void setCitizenshipType(CitizenshipType citizenshipType) {
        this.citizenshipType = citizenshipType;
    }
}