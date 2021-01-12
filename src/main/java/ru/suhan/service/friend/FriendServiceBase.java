package ru.suhan.service.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.suhan.domain.friend.Friend;
import ru.suhan.domain.friend.FriendBase;
import ru.suhan.entity.FriendEntity;
import ru.suhan.repository.FriendRepository;
import ru.suhan.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FriendServiceBase implements FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Override
    public Friend save(Friend friend) {
        return new FriendBase(
                this.friendRepository.save(
                        new FriendEntity(
                                friend.id(),
                                userRepository.getOne(friend.friendOf().id()),
                                friend.name(),
                                friend.description(),
                                friend.phoneNumber()
                        )
                )
        );
    }

    @Override
    public List<Friend> friendsByUserId(Long userId) {
        return this.friendRepository.findAllByUserEntityId(userId).stream().map(FriendBase::new).collect(Collectors.toList());
    }

}
