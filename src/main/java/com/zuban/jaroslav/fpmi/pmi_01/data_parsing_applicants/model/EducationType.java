package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "education_type")
public class EducationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "education_level")
    private String educationLevel;

    @OneToOne(mappedBy = "educationType")
    private Specification specification;

    public EducationType() {
    }
}
