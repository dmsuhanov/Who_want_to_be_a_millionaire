package ru.suhan.domain.user;


import ru.suhan.domain.IsExist;
import ru.suhan.domain.ToDto;
import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.friend.Friend;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.question.Question;

import java.util.List;
import java.util.Set;

public interface User extends ToDto<UserDto>, IsExist {

    Long id();

    String userName();

    Amount currentAmount();

    Amount guaranteeAmount();

    Set<Help> usedHelps();

    Boolean isEndGame();

    List<Friend> friends();

    User nextLevel(Amount amount);

    User gameOver(Amount zeroAmount);

    User endGame();

    User useHelp(Help help, Question question);

    Question questionToMistake();

}
