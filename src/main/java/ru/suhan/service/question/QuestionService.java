package ru.suhan.service.question;


import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.question.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Question> all();

    Question questionByLevel(Amount level);

    Question questionById(Long id);

    Question replaceQuestion(Long questionId);

}
