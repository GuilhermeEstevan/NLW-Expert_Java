package com.nlwproject.certification_nlw.modules.students.controllers;

import com.nlwproject.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.nlwproject.certification_nlw.modules.students.dto.VerifyIfHasCertificationDTO;
import com.nlwproject.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.nlwproject.certification_nlw.modules.students.services.StudentCertificationAnswersService;
import com.nlwproject.certification_nlw.modules.students.services.VerifyIfHasCertificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private VerifyIfHasCertificationService verifyIfHasCertificationService;
    @Autowired
    private StudentCertificationAnswersService studentCertificationAnswersService;

    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCertificationDTO data) {
        boolean result = verifyIfHasCertificationService.execute(data);
        if (result) {
            return "Usuário já fez a prova";
        }
        return "Usuário pode fazer a prova";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(
            @RequestBody StudentCertificationAnswerDTO data) {
        try {
            var result = studentCertificationAnswersService.execute(data);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
