package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "specification")
public class Specification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "ending")
    private int ending;

    @Column(name = "educational_institution")
    private String educational_institution;

    @Column(name = "direction")
    private String direction;

    @OneToOne
    @JoinColumn(name = "education_type_id", referencedColumnName = "id")
    private EducationType educationType;

    @ManyToMany(mappedBy = "specifications")
    private List<PersonalData> personalData;

    public Specification() {
    }

    public Specification(int ending, String educational_institution, String direction) {
        this.ending = ending;
        this.educational_institution = educational_institution;
        this.direction = direction;
    }
}