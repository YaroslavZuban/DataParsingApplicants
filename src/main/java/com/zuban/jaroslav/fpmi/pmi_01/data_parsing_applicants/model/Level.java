package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "knowledge_level")
    private String knowledgeLevel;

    @OneToOne(mappedBy = "Level")
    private Information information;

    public Level() {
    }

    public Level(String knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }
}
