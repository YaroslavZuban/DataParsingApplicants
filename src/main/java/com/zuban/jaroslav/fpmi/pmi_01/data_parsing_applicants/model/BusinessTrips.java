package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "business_trips")
public class BusinessTrips {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "business_trips_seq")
    @SequenceGenerator(name = "business_trips_seq", sequenceName = "business_trips_seq", allocationSize = 1)
    @Column(name = "id")
    private long id;

    @Column(name = "readiness")
    private String readiness;

    @OneToMany(mappedBy = "businessTrips")
    private List<Information> informationList;

    public BusinessTrips() {
    }

    public BusinessTrips(String readiness) {
        this.readiness = readiness;
    }
}
