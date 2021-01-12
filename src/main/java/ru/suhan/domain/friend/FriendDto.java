package ru.suhan.domain.friend;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FriendDto {

    private final Long id;
    private final String name;
    private final String description;
    private final String phoneNumber;

}
