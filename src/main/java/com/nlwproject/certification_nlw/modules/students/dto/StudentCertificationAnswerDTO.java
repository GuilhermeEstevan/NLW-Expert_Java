package com.nlwproject.certification_nlw.modules.students.dto;

import com.nlwproject.certification_nlw.modules.questions.dto.QuestionAnswersDTO;

import java.util.List;

public record StudentCertificationAnswerDTO(

        String email,
        String technology,
        List<QuestionAnswersDTO> questionAnswers

) {
}
