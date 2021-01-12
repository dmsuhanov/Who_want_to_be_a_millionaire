package ru.suhan.service.friend;

import ru.suhan.domain.friend.Friend;

import java.util.List;

public interface FriendService {

    Friend save(Friend friend);

    List<Friend> friendsByUserId(Long userId);

}
