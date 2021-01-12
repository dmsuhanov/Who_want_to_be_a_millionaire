package ru.suhan.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserBase;
import ru.suhan.entity.HelpEntity;
import ru.suhan.entity.TokenEntity;
import ru.suhan.entity.UserEntity;
import ru.suhan.form.LoginForm;
import ru.suhan.repository.*;
import ru.suhan.security.UserDetailsImpl;
import ru.suhan.service.friend.FriendService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceBase implements UserService {

    private final static Long START_WITH_AMOUNT = 0L;

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AmountRepository amountRepository;
    private final QuestionRepository questionRepository;
    private final FriendService friendService;

    @Override
    public User login(LoginForm loginForm) {
        UserEntity userEntity = this.userRepository.save(
                new UserEntity(
                        null,
                        loginForm.getLogin(),
                        null,
                        amountRepository.findOneByValue(START_WITH_AMOUNT).orElseThrow(RuntimeException::new),
                        false,
                        new HashSet<>(),
                        null
                ));
        TokenEntity tokenEntity = new TokenEntity();
        tokenEntity.setUserEntity(userEntity);
        tokenEntity.setValue(UUID.randomUUID().toString());
        this.tokenRepository.save(tokenEntity);
        return new UserBase(userEntity, this.friendService.friendsByUserId(userEntity.getId()));
    }

    @Override
    public User currentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails();
        return userDetails.getUser();
    }

    @Override
    public User save(User user) {
        return new UserBase(
                userRepository.save(
                        new UserEntity(
                                user.id(),
                                user.userName(),
                                amountRepository.findById(user.guaranteeAmount().id()).orElseThrow(RuntimeException::new),
                                amountRepository.findById(user.currentAmount().id()).orElseThrow(RuntimeException::new),
                                user.isEndGame(),
                                user.usedHelps().stream().map(help -> new HelpEntity(help.id(), help.name(), help.description())).collect(Collectors.toSet()),
                                user.questionToMistake() != null ? this.questionRepository.findById(user.questionToMistake().id()).orElseThrow(RuntimeException::new) : null
                        )
                ), user.id() != null ? this.friendService.friendsByUserId(user.id()) : new ArrayList<>()
        );
    }

    @Override
    public User userById(Long id) {
        UserEntity userEntity = this.userRepository.findById(id).orElseThrow(RuntimeException::new);
        return new UserBase(userEntity, this.friendService.friendsByUserId(id));
    }

}
