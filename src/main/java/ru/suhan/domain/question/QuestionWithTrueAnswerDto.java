package ru.suhan.domain.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.suhan.domain.answer.AnswerWithTrueFeatureDto;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class QuestionWithTrueAnswerDto {

    private final Long id;
    private final String questionText;
    private final List<AnswerWithTrueFeatureDto> answers;

}
