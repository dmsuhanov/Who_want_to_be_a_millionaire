package ru.suhan.service.token;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.suhan.domain.token.Token;
import ru.suhan.domain.token.TokenBase;
import ru.suhan.domain.token.TokenNotFound;
import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserBase;
import ru.suhan.entity.TokenEntity;
import ru.suhan.repository.TokenRepository;
import ru.suhan.service.friend.FriendService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenServiceBase implements TokenService  {

    private final TokenRepository tokenRepository;
    private final FriendService friendService;

    @Override
    public Token findByValue(String value) {
        final Optional<TokenEntity> tokenCandidate = this.tokenRepository.findOneByValue(value);
        if (tokenCandidate.isPresent()) {
            return new TokenBase(tokenCandidate.get().getValue(), new UserBase(tokenCandidate.get().getUserEntity(), friendService.friendsByUserId(tokenCandidate.get().getId())));
        }
        return new TokenNotFound();
    }

    @Override
    public Token tokenByUser(User user) {
        return new TokenBase(tokenRepository.findOneByUserEntityId(user.id()).orElseThrow(RuntimeException::new).getValue(), user);
    }

}
