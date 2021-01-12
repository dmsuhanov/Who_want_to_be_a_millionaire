package ru.suhan.domain.friend;

import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserBase;
import ru.suhan.entity.FriendEntity;

import java.util.ArrayList;

public class FriendBase implements Friend {

    private final Long id;
    private final String name;
    private final String description;
    private final String phoneNumber;
    private final User friendOf;

    public FriendBase(FriendEntity friendEntity) {
        this(friendEntity.getId(), friendEntity.getName(), friendEntity.getDescription(), friendEntity.getPhoneNumber(), new UserBase(friendEntity.getUserEntity(), new ArrayList<>()));
    }

    public FriendBase(Long id, String name, String description, String phoneNumber, User friendOf) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.friendOf = friendOf;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public String phoneNumber() {
        return this.phoneNumber;
    }

    @Override
    public User friendOf() {
        return this.friendOf;
    }

    @Override
    public FriendDto dto() {
        return new FriendDto(this.id, this.name, this.description, this.phoneNumber);
    }

}
