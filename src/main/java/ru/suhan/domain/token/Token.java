package ru.suhan.domain.token;


import ru.suhan.domain.IsExist;
import ru.suhan.domain.user.User;

public interface Token extends IsExist {

    String value();

    User user();

}
