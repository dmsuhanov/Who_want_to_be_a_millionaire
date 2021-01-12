package ru.suhan.domain.token;

import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserBase;
import ru.suhan.entity.TokenEntity;

public class TokenBase implements Token {

    private final String value;
    private final User user;

    public TokenBase(String value, User user) {
        this.value = value;
        this.user = user;
    }

    @Override
    public String value() {
        return this.value;
    }

    @Override
    public User user() {
        return this.user;
    }

    @Override
    public Boolean isExist() {
        return true;
    }
}
