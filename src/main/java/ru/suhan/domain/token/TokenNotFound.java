package ru.suhan.domain.token;

import ru.suhan.domain.user.User;

public class TokenNotFound implements Token {

    @Override
    public String value() {
        throw new RuntimeException("TokenNotFound");
    }

    @Override
    public User user() {
        throw new RuntimeException("TokenNotFound");
    }

    @Override
    public Boolean isExist() {
        return false;
    }
}
