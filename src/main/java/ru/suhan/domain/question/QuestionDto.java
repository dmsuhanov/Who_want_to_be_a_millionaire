package ru.suhan.domain.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.suhan.domain.answer.AnswerDto;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class QuestionDto {

    private final Long id;
    private final String questionText;
    private final List<AnswerDto> answers;

}
