package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "licence_category")
public class LicenceCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "category")
    private String category;

    @ManyToOne
    private Information information;

    public LicenceCategory() {
    }

    public LicenceCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}