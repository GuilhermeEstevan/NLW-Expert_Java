package com.nlwproject.certification_nlw.modules.certifications.controllers;

import com.nlwproject.certification_nlw.modules.certifications.services.Top10RankingService;
import com.nlwproject.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.nlwproject.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    Top10RankingService top10RankingService;

    @GetMapping("/top10")
    public List<CertificationStudentEntity> top10() {
        return top10RankingService.execute();
    }
}
