package ru.suhan.domain.answer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AnswerDto {

    private final Long id;
    private final String answerText;

}
