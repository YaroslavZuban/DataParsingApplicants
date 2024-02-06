package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees_experience")
public class EmployeesExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "post")
    private String post;

    @Column(name = "responsibilities")
    private String responsibilities;

    @Column(name = "company")
    private String company;

    @Column(name = "employment_opportunities")
    private String employmentOpportunities;

    @Column(name = "dismissal")
    private String dismissal;

    public EmployeesExperience() {
    }

    public EmployeesExperience(String post, String responsibilities, String company, String employmentOpportunities, String dismissal) {
        this.post = post;
        this.responsibilities = responsibilities;
        this.company = company;
        this.employmentOpportunities = employmentOpportunities;
        this.dismissal = dismissal;
    }

    public int getId() {
        return id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmploymentOpportunities() {
        return employmentOpportunities;
    }

    public void setEmploymentOpportunities(String employmentOpportunities) {
        this.employmentOpportunities = employmentOpportunities;
    }

    public String getDismissal() {
        return dismissal;
    }

    public void setDismissal(String dismissal) {
        this.dismissal = dismissal;
    }
}