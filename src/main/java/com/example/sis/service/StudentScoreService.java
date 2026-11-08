package com.example.sis.service;

import com.example.sis.entity.StudentScore;
import com.example.sis.repository.StudentScoreRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentScoreService {

    private final StudentScoreRepository scoreRepository;

    public StudentScoreService(
            StudentScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<StudentScore> findAll() {
        return scoreRepository.findAll();
    }

    public StudentScore save(StudentScore score) {
        return scoreRepository.save(score);
    }
}