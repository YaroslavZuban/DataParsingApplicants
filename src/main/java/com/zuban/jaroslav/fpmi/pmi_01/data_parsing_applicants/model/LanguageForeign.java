package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "language_foreign")
public class LanguageForeign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Language language;
    private Level level;

    public LanguageForeign() {
    }

    public LanguageForeign(Language language, Level level) {
        this.language = language;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}