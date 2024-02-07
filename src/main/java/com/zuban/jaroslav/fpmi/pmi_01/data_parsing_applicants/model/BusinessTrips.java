package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "business_trip")
public class BusinessTrips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "readiness")
    private String readiness;

    @OneToOne(mappedBy = "businessTrips")
    private Information information;

    public BusinessTrips() {
    }

    public BusinessTrips(String readiness) {
        this.readiness = readiness;
    }

    public long getId() {
        return id;
    }

    public String getReadiness() {
        return readiness;
    }

    public void setReadiness(String readiness) {
        this.readiness = readiness;
    }
}
