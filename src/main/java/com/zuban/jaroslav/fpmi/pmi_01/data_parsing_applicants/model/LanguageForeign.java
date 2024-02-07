package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "language_foreign")
public class LanguageForeign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private List<Language> language;

    @OneToMany
    @JoinColumn(name = "language_level_id", referencedColumnName = "id")
    private List<Level> level;

    @ManyToOne
    private Information information;

    public LanguageForeign() {
    }

    public int getId() {
        return id;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public void setLanguage(List<Language> language) {
        this.language = language;
    }

    public List<Level> getLevel() {
        return level;
    }

    public void setLevel(List<Level> level) {
        this.level = level;
    }
}