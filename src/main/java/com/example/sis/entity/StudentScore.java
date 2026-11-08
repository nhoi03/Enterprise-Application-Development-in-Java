package com.example.sis.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "student_score_t")
public class StudentScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private Integer studentScoreId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "score1")
    private BigDecimal score1;

    @Column(name = "score2")
    private BigDecimal score2;

    @Transient
    public double getFinalScore() {
        if (score1 == null || score2 == null) {
            return 0;
        }

        return score1.doubleValue() * 0.3
                + score2.doubleValue() * 0.7;
    }

    @Transient
    public String getGrade() {
        if (score1 == null || score2 == null) {
            return "N/A";
        }

        double score = getFinalScore();

        if (score >= 8) return "A";
        if (score >= 6) return "B";
        if (score >= 4) return "D";
        return "F";
    }

    public Integer getStudentScoreId() {
        return studentScoreId;
    }

    public void setStudentScoreId(Integer studentScoreId) {
        this.studentScoreId = studentScoreId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public BigDecimal getScore1() {
        return score1;
    }

    public void setScore1(BigDecimal score1) {
        this.score1 = score1;
    }

    public BigDecimal getScore2() {
        return score2;
    }

    public void setScore2(BigDecimal score2) {
        this.score2 = score2;
    }
}