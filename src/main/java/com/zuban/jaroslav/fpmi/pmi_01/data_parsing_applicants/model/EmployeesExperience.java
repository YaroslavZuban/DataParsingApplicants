package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "employees_experience")
public class EmployeesExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_experience_seq")
    @SequenceGenerator(name = "employees_experience_seq", sequenceName = "employees_experience_seq", allocationSize = 1)
    private int id;

    @Column(name = "post")
    private String post;

    @Column(name = "responsibilities")
    private String responsibilities; //Обязанности

    @Column(name = "company")
    private String company;

    @ManyToMany
    @JoinTable(
            name = "work",
            joinColumns = @JoinColumn(name = "employees_experience_id "),
            inverseJoinColumns = @JoinColumn(name = "personal_data_id"))
    private List<PersonalData> personalDataList;

    public EmployeesExperience() {
    }

    public EmployeesExperience(String post, String responsibilities, String company) {
        this.post = post;
        this.responsibilities = responsibilities;
        this.company = company;
    }
}