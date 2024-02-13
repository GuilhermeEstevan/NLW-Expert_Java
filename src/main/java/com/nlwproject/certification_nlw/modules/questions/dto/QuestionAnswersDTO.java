package com.nlwproject.certification_nlw.modules.questions.dto;

import com.nlwproject.certification_nlw.modules.questions.entities.AlternativesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswersDTO {

    private UUID questionID;
    private UUID alternativeID;
    private boolean isCorrect;


}