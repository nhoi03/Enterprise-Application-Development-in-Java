package com.example.sis.controller;

import com.example.sis.entity.Student;
import com.example.sis.service.StudentService;
import com.example.sis.service.StudentScoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final StudentScoreService scoreService;

    public StudentController(
            StudentService studentService,
            StudentScoreService scoreService) {
        this.studentService = studentService;
        this.scoreService = scoreService;
    }

    @GetMapping({"/", "/students"})
    public String list(Model model) {
        model.addAttribute("scores", scoreService.findAll());
        return "students/list";
    }

    @GetMapping("/students/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "students/create";
    }

    @PostMapping("/students/save")
    public String save(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students";
    }
}