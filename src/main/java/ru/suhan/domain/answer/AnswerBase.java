package ru.suhan.domain.answer;


import ru.suhan.domain.help.Help;
import ru.suhan.domain.help.HelpName;
import ru.suhan.entity.AnswerEntity;

public class AnswerBase implements Answer {

    private final Long id;
    private final String answerText;
    private final Boolean isTrueAnswer;
    private final Integer peopleHelpPercent;
    private final Boolean isFriendChoice;

    public AnswerBase(AnswerEntity answerEntity) {
        this(answerEntity.getId(), answerEntity.getAnswerText(), answerEntity.getIsTrueAnswer(), answerEntity.getPeopleHelpPercent(), answerEntity.getIsFriendChoice());
    }

    public AnswerBase(Long id, String answerText, Boolean isTrueAnswer, Integer peopleHelpPercent, Boolean isFriendChoice) {
        this.id = id;
        this.answerText = answerText;
        this.isTrueAnswer = isTrueAnswer;
        this.peopleHelpPercent = peopleHelpPercent;
        this.isFriendChoice = isFriendChoice;

    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String answerText() {
        return this.answerText;
    }

    @Override
    public Boolean isTrueAnswer() {
        return this.isTrueAnswer;
    }

    @Override
    public Integer peopleHelpPercent() {
        return this.peopleHelpPercent;
    }

    @Override
    public Boolean isFriendChoice() {
        return this.isFriendChoice;
    }

    @Override
    public AnswerDto dto() {
        return new AnswerDto(this.id, this.answerText);
    }

    @Override
    public AnswerWithHelpDto dtoWithHelp(Help help) {
        if (help.name().equals(HelpName.PEOPLE.getCodeName())) {
            return new AnswerWithHelpDto(this.id, this.answerText, this.peopleHelpPercent, true, null);
        }
        if (help.name().equals(HelpName.CALL_FRIEND.getCodeName())) {
            return new AnswerWithHelpDto(this.id, this.answerText, null, true, this.isFriendChoice);
        }
        return new AnswerWithHelpDto(this.id, this.answerText, null, true, null);
    }

    @Override
    public AnswerWithTrueFeatureDto dtoWithTrueFeature() {
        return new AnswerWithTrueFeatureDto(this.id, this.answerText, this.isTrueAnswer);
    }
}
