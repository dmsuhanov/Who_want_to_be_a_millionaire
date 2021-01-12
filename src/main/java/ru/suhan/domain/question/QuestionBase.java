package ru.suhan.domain.question;

import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.amount.AmountBase;
import ru.suhan.domain.answer.Answer;
import ru.suhan.domain.answer.AnswerWithHelpDto;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.help.HelpName;
import ru.suhan.entity.QuestionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionBase implements Question {

    private final Long id;
    private final String questionText;
    private final Amount level;
    private final List<Answer> answers;

    public QuestionBase(QuestionEntity questionEntity, List<Answer> answers) {
        this(questionEntity.getId(), questionEntity.getQuestionText(),
                new AmountBase(questionEntity.getLevelAmountEntity()), answers);
    }

    public QuestionBase(Long id, String questionText, Amount level, List<Answer> answers) {
        this.id = id;
        this.questionText = questionText;
        this.level = level;
        this.answers = answers;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String questionText() {
        return this.questionText;
    }

    @Override
    public Amount level() {
        return this.level;
    }

    @Override
    public List<Answer> answers() {
        return this.answers;
    }

    @Override
    public Answer findAnswerById(Long id) {
        return this.answers.stream().filter(answer -> answer.id().equals(id)).findFirst().orElseThrow(RuntimeException::new);
    }

    @Override
    public QuestionDto dto() {
        return new QuestionDto(
                this.id,
                this.questionText,
                this.answers
                        .stream()
                        .map(Answer::dto)
                        .collect(Collectors.toList()));
    }

    @Override
    public QuestionWithHelpDto dtoWithHelp(Help help) {
        final Long idAnswerToShowIfFalseAnswer = this.answers
                .stream()
                .filter(answer -> !answer.isTrueAnswer())
                .map(Answer::id)
                .findAny()
                .orElseThrow(RuntimeException::new);

        return new QuestionWithHelpDto(
                this.id,
                this.questionText,
                this.answers
                        .stream()
                        .map(answer -> {
                            AnswerWithHelpDto answerWithHelpDto = answer.dtoWithHelp(help);
                            if (help.name().equals(HelpName.FIFTY_FIFTY.getCodeName())) {
                                return new AnswerWithHelpDto(
                                        answerWithHelpDto.getId(),
                                        answerWithHelpDto.getAnswerText(),
                                        answerWithHelpDto.getPeopleHelpPercent(),
                                        answer.isTrueAnswer() || (answer.id().equals(idAnswerToShowIfFalseAnswer)),
                                        answerWithHelpDto.getIsFriendChoice()
                                );
                            }
                            return answerWithHelpDto;
                        })
                        .collect(Collectors.toList())
        );
    }

    @Override
    public QuestionWithTrueAnswerDto dtoWithTrueAnswer() {
        return new QuestionWithTrueAnswerDto(
                this.id,
                this.questionText,
                this.answers.stream().map(Answer::dtoWithTrueFeature).collect(Collectors.toList()));
    }

}
