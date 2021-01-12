package ru.suhan.service.user;

import ru.suhan.domain.user.User;
import ru.suhan.form.LoginForm;

public interface UserService {

    User login(LoginForm loginForm);

    User currentUser();

    User save(User user);

    User userById(Long id);

}
