package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "habitation")
public class Habitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "habitation")
    private PersonalData personalData;

    public Habitation() {
    }

    public Habitation(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}