package ru.suhan.domain.user;

import ru.suhan.domain.ToDto;
import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.amount.AmountBase;
import ru.suhan.domain.friend.Friend;
import ru.suhan.domain.friend.FriendBase;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.help.HelpBase;
import ru.suhan.domain.help.HelpName;
import ru.suhan.domain.question.Question;
import ru.suhan.domain.question.QuestionBase;
import ru.suhan.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class UserBase implements User {

    private final Long id;
    private final String userName;
    private final Amount currentAmount;
    private final Amount guaranteeAmount;
    private final Boolean isEndGame;
    private final Set<Help> usedHelps;
    private final Question questionToMistake;
    private final List<Friend> friends;

    public UserBase(UserEntity userEntity, List<Friend> friends) {
        this(
                userEntity.getId(),
                userEntity.getLogin(),
                new AmountBase(userEntity.getCurrentAmountEntity()),
                userEntity.getGuaranteeAmountEntity() != null ? new AmountBase(userEntity.getGuaranteeAmountEntity()) : null,
                userEntity.getIsEndGame(),
                userEntity.getHelpEntitySet().stream().map(HelpBase::new).collect(Collectors.toSet()),
                userEntity.getQuestionEntityMistakeRight() != null ? new QuestionBase(userEntity.getQuestionEntityMistakeRight(), new ArrayList<>()) : null,
                friends
        );
    }

    public UserBase(Long id, String userName, Amount currentAmount, Amount guaranteeAmount, Boolean isEndGame,
                    Set<Help> usedHelps, Question questionToMistake, List<Friend> friends) {
        this.id = id;
        this.userName = userName;
        this.currentAmount = currentAmount;
        this.guaranteeAmount = guaranteeAmount;
        this.isEndGame = isEndGame;
        this.usedHelps = usedHelps;
        this.questionToMistake = questionToMistake;
        this.friends = friends;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String userName() {
        return this.userName;
    }

    @Override
    public Amount currentAmount() {
        return this.currentAmount;
    }

    @Override
    public Amount guaranteeAmount() {
        return this.guaranteeAmount;
    }

    @Override
    public Set<Help> usedHelps() {
        return this.usedHelps;
    }

    @Override
    public Boolean isEndGame() {
        return this.isEndGame;
    }

    @Override
    public List<Friend> friends() {
        return null;
    }

    @Override
    public User nextLevel(Amount amount) {
        return new UserBase(
                this.id,
                this.userName,
                amount,
                this.guaranteeAmount,
                this.isEndGame,
                this.usedHelps,
                this.questionToMistake,
                this.friends
        );
    }

    @Override
    public User gameOver(Amount zeroAmount) {
        return new UserBase(
                this.id,
                this.userName,
                this.currentAmount.value() >= this.guaranteeAmount.value() ? this.guaranteeAmount : zeroAmount,
                this.guaranteeAmount,
                true,
                this.usedHelps,
                this.questionToMistake,
                this.friends
        );
    }

    @Override
    public User endGame() {
        return new UserBase(
                this.id,
                this.userName,
                this.currentAmount,
                this.guaranteeAmount,
                true,
                this.usedHelps,
                this.questionToMistake,
                this.friends
        );
    }

    @Override
    public User useHelp(Help help, Question question) {
        final Set<Help> usedHelp = this.usedHelps;
        usedHelp.add(help);
        return new UserBase(
                this.id,
                this.userName,
                this.currentAmount,
                this.guaranteeAmount,
                this.isEndGame,
                usedHelp,
                help.name().equals(HelpName.MISTAKES.getCodeName()) ? question : null,
                this.friends
        );
    }

    @Override
    public Question questionToMistake() {
        return this.questionToMistake;
    }

    @Override
    public Boolean isExist() {
        return true;
    }

    @Override
    public UserDto dto() {
        return new UserDto(
                this.id,
                this.userName,
                this.currentAmount.value(),
                this.guaranteeAmount != null ? this.guaranteeAmount.value() : null,
                this.isEndGame,
                this.usedHelps.stream().map(ToDto::dto).collect(Collectors.toList()),
                this.friends.stream().map(Friend::dto).collect(Collectors.toList())
        );
    }


}
