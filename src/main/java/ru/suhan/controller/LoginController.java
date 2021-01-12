package ru.suhan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserDto;
import ru.suhan.form.LoginForm;
import ru.suhan.service.token.TokenService;
import ru.suhan.service.user.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final TokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) {
        final User user = this.userService.login(loginForm);
        if (user.isExist()) {
            response.addCookie(new Cookie("Auth-Token", tokenService.tokenByUser(user).value()));
        }
        return user.dto();
    }

}
