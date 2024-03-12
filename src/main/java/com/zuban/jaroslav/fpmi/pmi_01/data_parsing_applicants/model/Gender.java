package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "gender")
public class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_seq")
    @SequenceGenerator(name = "gender_seq", sequenceName = "gender_seq", allocationSize = 1)
    private int id;

    @Column(name = "type")
    private String type;

    @OneToOne(mappedBy = "gender")
    private PersonalData personalData;

    public Gender() {
    }

    public Gender(String type) {
        this.type = type;
    }
}