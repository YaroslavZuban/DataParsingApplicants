package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
}