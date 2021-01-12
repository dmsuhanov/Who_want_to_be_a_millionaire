package ru.suhan.domain.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.suhan.domain.friend.FriendDto;
import ru.suhan.domain.help.HelpDto;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class UserDto {

    private final Long id;
    private final String userName;
    private final Long currentAmount;
    private final Long guaranteeAmount;
    private final Boolean isEndGame;
    private final List<HelpDto> usedHelps;
    private final List<FriendDto> friends;

}
