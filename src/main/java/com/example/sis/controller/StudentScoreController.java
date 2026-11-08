package com.example.sis.controller;

import com.example.sis.entity.StudentScore;
import com.example.sis.repository.SubjectRepository;
import com.example.sis.service.StudentService;
import com.example.sis.service.StudentScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/scores")
public class StudentScoreController {

    private final StudentScoreService scoreService;
    private final StudentService studentService;
    private final SubjectRepository subjectRepository;

    public StudentScoreController(
            StudentScoreService scoreService,
            StudentService studentService,
            SubjectRepository subjectRepository) {
        this.scoreService = scoreService;
        this.studentService = studentService;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("score", new StudentScore());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("subjects", subjectRepository.findAll());
        return "scores/create";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam Integer studentId,
            @RequestParam Integer subjectId,
            @RequestParam java.math.BigDecimal score1,
            @RequestParam java.math.BigDecimal score2) {

        if (score1 == null || score2 == null
                || score1.compareTo(java.math.BigDecimal.ZERO) < 0
                || score2.compareTo(java.math.BigDecimal.ZERO) < 0
                || score1.compareTo(java.math.BigDecimal.TEN) > 0
                || score2.compareTo(java.math.BigDecimal.TEN) > 0) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Điểm phải nằm trong khoảng 0 đến 10");
        }

        StudentScore score = new StudentScore();

        score.setStudent(
                studentService.findAll().stream()
                        .filter(s -> s.getStudentId().equals(studentId))
                        .findFirst()
                        .orElseThrow(() ->
                                new org.springframework.web.server.ResponseStatusException(
                                        org.springframework.http.HttpStatus.BAD_REQUEST,
                                        "Sinh viên không tồn tại"))
        );

        score.setSubject(
                subjectRepository.findById(subjectId)
                        .orElseThrow(() ->
                                new org.springframework.web.server.ResponseStatusException(
                                        org.springframework.http.HttpStatus.BAD_REQUEST,
                                        "Môn học không tồn tại"))
        );

        score.setScore1(score1);
        score.setScore2(score2);

        scoreService.save(score);

        return "redirect:/students";
    }
}