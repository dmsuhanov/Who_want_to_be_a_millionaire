package ru.suhan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserBase;
import ru.suhan.domain.user.UserDto;
import ru.suhan.service.amount.AmountService;
import ru.suhan.service.user.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AmountService amountService;

    @PutMapping("selectGuaranteeAmount/{id:\\d+}")
    public UserDto selectGuaranteeAmount(@PathVariable Long id) {
        User user = userService.currentUser();
        return userService.save(
                new UserBase(
                        user.id(),
                        user.userName(),
                        user.currentAmount(),
                        amountService.findById(id),
                        user.isEndGame(),
                        user.usedHelps(),
                        user.questionToMistake(),
                        user.friends()
                )
        ).dto();
    }
}
