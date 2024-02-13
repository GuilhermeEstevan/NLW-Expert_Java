package com.nlwproject.certification_nlw.modules.students.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certifications")
public class CertificationStudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100)
    private String technology;

    @Column(length = 10)
    private int grade;

    @Column(name = "student_id")
    private UUID student;

    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;

    @OneToMany(mappedBy = "certificationStudentEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AnswersCertificationsEntity> answersCertificationsEntity;

    @CreationTimestamp
    private LocalDateTime createdAt;

}