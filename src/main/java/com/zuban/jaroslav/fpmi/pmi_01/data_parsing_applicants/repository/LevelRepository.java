package com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.repository;

import com.zuban.jaroslav.fpmi.pmi_01.data_parsing_applicants.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Level findByKnowledgeLevel(String knowledgeLevel);
    boolean existsByKnowledgeLevel(String knowledgeLevel);
}
