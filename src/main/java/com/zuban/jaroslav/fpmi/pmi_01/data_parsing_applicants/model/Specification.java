package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.processing.Generated;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "specification")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specification_seq")
    @SequenceGenerator(name = "specification_seq", sequenceName = "specification_seq", allocationSize = 1)
    private int id;

    @Column(name = "ending")
    private int ending;

    @Column(name = "educational_institution")
    private String educationalInstitution;

    @Column(name = "direction")
    private String direction;

    @OneToOne
    @JoinColumn(name = "education_type_id", referencedColumnName = "id")
    private EducationType educationType;

    @ManyToMany(mappedBy = "specifications")
    private List<PersonalData> personalData;

    public Specification() {
    }

    public Specification(int ending, String educationalInstitution, String direction, EducationType educationType) {
        this.ending = ending;
        this.educationalInstitution = educationalInstitution;
        this.direction = direction;
        this.educationType = educationType;
    }
}