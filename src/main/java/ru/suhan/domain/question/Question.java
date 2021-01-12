package ru.suhan.domain.question;


import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.answer.Answer;
import ru.suhan.domain.help.Help;

import java.util.List;

public interface Question {

    Long id();

    String questionText();

    Amount level();

    List<Answer> answers();

    Answer findAnswerById(Long id);

    QuestionDto dto();

    QuestionWithHelpDto dtoWithHelp(Help help);

    QuestionWithTrueAnswerDto dtoWithTrueAnswer();

}
