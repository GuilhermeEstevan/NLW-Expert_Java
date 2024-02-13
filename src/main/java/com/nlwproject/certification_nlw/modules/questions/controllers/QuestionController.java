package com.nlwproject.certification_nlw.modules.questions.controllers;

import com.nlwproject.certification_nlw.modules.questions.dto.QuestionResultDTO;
import com.nlwproject.certification_nlw.modules.questions.entities.QuestionEntity;
import com.nlwproject.certification_nlw.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(
            @PathVariable("technology") String technology) {

        var result = questionRepository.findByTechnology(technology);
        List<QuestionResultDTO> questionResultDTOS =
                result.stream().map(QuestionResultDTO::new).toList();
        return questionResultDTOS;
    }


}
