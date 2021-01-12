package ru.suhan.domain.answer;

import lombok.Getter;

@Getter
public class AnswerWithHelpDto extends AnswerDto {

    private final Integer peopleHelpPercent;
    private final Boolean isShow;
    private final Boolean isFriendChoice;

    public AnswerWithHelpDto(Long id, String answerText, Integer peopleHelpPercent, Boolean isShow, Boolean isFriendChoice) {
        super(id, answerText);
        this.peopleHelpPercent = peopleHelpPercent;
        this.isShow = isShow;
        this.isFriendChoice = isFriendChoice;
    }
}
