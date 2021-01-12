package ru.suhan.domain.friend;


import ru.suhan.domain.user.User;

public interface Friend {

    Long id();

    String name();

    String description();

    String phoneNumber();

    User friendOf();

    FriendDto dto();

}
