package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "habitation_id", referencedColumnName = "id")
    private Habitation habitation;

    @OneToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private Gender gender;

    @Column(name = "birth_data")
    @Temporal(TemporalType.DATE)
    private Date birthData;

    @OneToOne
    @JoinColumn(name = "add_information_id", referencedColumnName = "id")
    private Information information;

    @ManyToMany(mappedBy = "personalDataList")
    private List<EmployeesExperience> experienceList;

    @ManyToMany
    @JoinTable(name = "employment_type",
            joinColumns = @JoinColumn(name = "personal_data_id"),
            inverseJoinColumns = @JoinColumn(name = "work_schedule_id"))
    private List<WorkSchedule> workSchedule;

    @ManyToMany
    @JoinTable(name = "educatio",
            joinColumns = @JoinColumn(name = "personal_data_id"),
            inverseJoinColumns = @JoinColumn(name = "specification_id"))
    private List<Specification> specifications;

    @ManyToMany
    @JoinTable(name = "citizenship",
            joinColumns = @JoinColumn(name = "personal_data_id"),
            inverseJoinColumns = @JoinColumn(name = "citizenship_type_id"))
    private List<CitizenshipType> citizenshipType;

    public PersonalData() {
    }

    public PersonalData(String name) {
        this.name = name;
    }
}
