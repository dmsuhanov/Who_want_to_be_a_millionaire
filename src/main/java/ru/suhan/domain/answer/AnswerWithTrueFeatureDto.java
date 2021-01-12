package ru.suhan.domain.answer;

import lombok.Getter;

@Getter
public class AnswerWithTrueFeatureDto extends AnswerDto {

    private final Boolean isTrueAnswer;

    public AnswerWithTrueFeatureDto(Long id, String answerText, Boolean isTrueAnswer) {
        super(id, answerText);
        this.isTrueAnswer = isTrueAnswer;
    }
}
