package com.example.sis.repository;

import com.example.sis.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository
        extends JpaRepository<Subject, Integer> {
}