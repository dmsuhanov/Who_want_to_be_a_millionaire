package ru.suhan.domain.question;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.suhan.domain.answer.AnswerWithHelpDto;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class QuestionWithHelpDto {

    private final Long id;
    private final String questionText;
    private final List<AnswerWithHelpDto> answers;

}
