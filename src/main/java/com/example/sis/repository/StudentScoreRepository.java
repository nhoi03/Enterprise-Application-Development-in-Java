package com.example.sis.repository;

import com.example.sis.entity.StudentScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentScoreRepository
        extends JpaRepository<StudentScore, Integer> {
}