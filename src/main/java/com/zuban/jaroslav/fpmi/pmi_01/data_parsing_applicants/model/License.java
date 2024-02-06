package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "license")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Information information;
    private LicenceCategory licenceCategory;

    public License() {
    }

    public License(Information information, LicenceCategory licenceCategory) {
        this.information = information;
        this.licenceCategory = licenceCategory;
    }

    public int getId() {
        return id;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public LicenceCategory getLicenceCategory() {
        return licenceCategory;
    }

    public void setLicenceCategory(LicenceCategory licenceCategory) {
        this.licenceCategory = licenceCategory;
    }
}
