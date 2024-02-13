package com.nlwproject.certification_nlw.modules.questions.dto;

import com.nlwproject.certification_nlw.modules.questions.entities.AlternativesEntity;

import java.util.UUID;

public record AlternativesResultDTO(

        UUID id,
        String description
) {
    public AlternativesResultDTO(AlternativesEntity alternativesEntity) {
        this(
                alternativesEntity.getId(),
                alternativesEntity.getDescription()
        );
    }
}
