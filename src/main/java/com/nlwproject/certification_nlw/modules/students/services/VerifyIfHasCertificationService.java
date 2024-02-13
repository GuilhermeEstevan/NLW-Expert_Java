package com.nlwproject.certification_nlw.modules.students.services;

import com.nlwproject.certification_nlw.modules.students.dto.VerifyIfHasCertificationDTO;
import com.nlwproject.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationService {

    @Autowired
    CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyIfHasCertificationDTO data) {
        var result = certificationStudentRepository.
                findByStudentEmailAndTechnology(data.email(), data.technology());
        if (!result.isEmpty()) {
            return true;
        }
        return false;
    }
}
