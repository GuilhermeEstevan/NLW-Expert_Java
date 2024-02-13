package com.nlwproject.certification_nlw.modules.students.services;

import com.nlwproject.certification_nlw.modules.questions.entities.QuestionEntity;
import com.nlwproject.certification_nlw.modules.questions.repositories.QuestionRepository;
import com.nlwproject.certification_nlw.modules.students.dto.StudentCertificationAnswerDTO;
import com.nlwproject.certification_nlw.modules.students.dto.VerifyIfHasCertificationDTO;
import com.nlwproject.certification_nlw.modules.students.entities.AnswersCertificationsEntity;
import com.nlwproject.certification_nlw.modules.students.entities.CertificationStudentEntity;
import com.nlwproject.certification_nlw.modules.students.entities.StudentEntity;
import com.nlwproject.certification_nlw.modules.students.repositories.CertificationStudentRepository;
import com.nlwproject.certification_nlw.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswersService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationService verifyIfHasCertificationService;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO data) throws Exception {
        // VERIFICAR SE USUARIO EXISTE
        var optionalStudent = studentRepository.findByEmail(data.email());
        UUID studentId;

        if (optionalStudent.isEmpty()) {
            StudentEntity studentCreated = StudentEntity.builder().email(data.email()).build();
            var student = studentRepository.save(studentCreated);
            studentId = student.getId();
        } else {
            var student = optionalStudent.get();
            studentId = student.getId();
        }

        var hasCertification = verifyIfHasCertificationService.execute(new VerifyIfHasCertificationDTO(data.email(), data.technology()));
        if (hasCertification) {
            throw new Exception("Você já possui essa certificação");
        }


        // BUSCAR ALTERNATIVAS DAS PERGUNTAS
        List<QuestionEntity> questionEntities = questionRepository.findByTechnology(data.technology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();
        AtomicInteger correctAnswers = new AtomicInteger(0);

        data.questionAnswers().stream().forEach(questionAnswer -> {

            var question = questionEntities.stream().filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
                    .findFirst().get();

            var correctAlternative = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect())
                    .findFirst().get();

            if (correctAlternative.getId().equals(questionAnswer.getAlternativeID())) {
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else {
                questionAnswer.setCorrect(false);
            }

            var answersCertification = AnswersCertificationsEntity.builder()
                    .answerID(questionAnswer.getAlternativeID())
                    .questionID(questionAnswer.getQuestionID())
                    .isCorrect(questionAnswer.isCorrect())
                    .studentID(studentId)
                    .build();

            answersCertifications.add(answersCertification);
        });


        CertificationStudentEntity certificationStudentEntity = new CertificationStudentEntity();
        certificationStudentEntity.setStudent(studentId);
        certificationStudentEntity.setTechnology(data.technology());
        certificationStudentEntity.setGrade(correctAnswers.get());

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudentCreated.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentCreated);
        });

        certificationStudentCreated.setAnswersCertificationsEntity(answersCertifications);

        certificationStudentRepository.save(certificationStudentCreated);

        return certificationStudentCreated;


        // SALVAR AS INFORMAÇÕES DA CERTIFICAÇÃO
    }
}
