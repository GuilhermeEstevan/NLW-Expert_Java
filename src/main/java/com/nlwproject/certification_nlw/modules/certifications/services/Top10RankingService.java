package com.nlwproject.certification_nlw.modules.certifications.services;

import com.nlwproject.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.nlwproject.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingService {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute() {
        return certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }
}
