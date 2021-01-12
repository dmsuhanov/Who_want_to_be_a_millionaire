package ru.suhan.domain.answer;


import ru.suhan.domain.help.Help;

public interface Answer {

    Long id();

    String answerText();

    Boolean isTrueAnswer();

    Integer peopleHelpPercent();

    Boolean isFriendChoice();

    AnswerDto dto();

    AnswerWithHelpDto dtoWithHelp(Help help);

    AnswerWithTrueFeatureDto dtoWithTrueFeature();

}
