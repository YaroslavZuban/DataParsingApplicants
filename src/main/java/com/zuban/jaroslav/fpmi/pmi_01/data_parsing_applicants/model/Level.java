package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "knowledge_level")
    private String knowledgeLevel;

    @ManyToOne
    private LanguageForeign languageForeign;



    public Level() {
    }

    public Level(String knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }

    public int getId() {
        return id;
    }

    public String getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(String knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }
}
