package ru.suhan.service.token;


import ru.suhan.domain.token.Token;
import ru.suhan.domain.user.User;

public interface TokenService {

    Token findByValue(final String value);

    Token tokenByUser(final User user);

}
