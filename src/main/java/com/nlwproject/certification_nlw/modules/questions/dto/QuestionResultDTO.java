package com.nlwproject.certification_nlw.modules.questions.dto;

import com.nlwproject.certification_nlw.modules.questions.entities.QuestionEntity;

import java.util.List;
import java.util.UUID;

public record QuestionResultDTO(

        UUID id,
        String technology,
        String description,
        List<AlternativesResultDTO> alternatives
) {
    public QuestionResultDTO(QuestionEntity questionEntity) {
        this(
                questionEntity.getId(),
                questionEntity.getTechnology(),
                questionEntity.getDescription(),
                questionEntity.getAlternatives()
                        .stream().map(AlternativesResultDTO::new).toList()
        );
    }


}
