package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "citizenship_type")
public class CitizenshipType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citizenship_type_seq")
    @SequenceGenerator(name = "citizenship_type_seq", sequenceName = "citizenship_type_seq", allocationSize = 1)
    @Column(name = "id")
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
}