package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "business_trips")
public class BusinessTrips {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_trips_seq")
    @SequenceGenerator(name = "business_trips_seq", sequenceName = "business_trips_seq", allocationSize = 1)
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
}
