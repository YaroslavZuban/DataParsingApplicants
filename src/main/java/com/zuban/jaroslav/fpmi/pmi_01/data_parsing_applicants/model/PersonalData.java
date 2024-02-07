package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "personal_data")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

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

    public PersonalData(String firstName, String lastName, String patronymic, Habitation habitation,
                        Gender gender, Date birthData, Information information) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.habitation = habitation;
        this.gender = gender;
        this.birthData = birthData;
        this.information = information;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Habitation getHabitation() {
        return habitation;
    }

    public void setHabitation(Habitation habitation) {
        this.habitation = habitation;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthData() {
        return birthData;
    }

    public void setBirthData(Date birthData) {
        this.birthData = birthData;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }
}
