package ru.suhan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.suhan.domain.friend.Friend;
import ru.suhan.domain.friend.FriendBase;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.help.HelpName;
import ru.suhan.domain.question.Question;
import ru.suhan.domain.user.User;
import ru.suhan.domain.user.UserDto;
import ru.suhan.form.FriendForm;
import ru.suhan.service.friend.FriendService;
import ru.suhan.service.help.HelpService;
import ru.suhan.service.question.QuestionService;
import ru.suhan.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

    private final static Long TIME_FRIEND_THINKS = 3000L;

    private final UserService userService;
    private final FriendService friendService;
    private final HelpService helpService;
    private final QuestionService questionService;

    @PostMapping
    public UserDto addFriend(@RequestBody FriendForm friendForm) {
        final User user = this.userService.currentUser();
        final Friend friend = new FriendBase(
                null,
                friendForm.getName(),
                friendForm.getDescription(),
                friendForm.getPhoneNumber(),
                user
        );
        this.friendService.save(friend);
        return userService.userById(user.id()).dto();
    }

    @PutMapping("/{friendId:\\d+}/needHelp/{questionId:\\d+}")
    public Map<String, Object> selectHelp(@PathVariable Long questionId, @PathVariable Long friendId) throws InterruptedException {
        final Map<String, Object> result = new HashMap<>();
        final User currentUser = userService.currentUser();
        final Help helpToUse = this.helpService.findByName(HelpName.CALL_FRIEND.getCodeName());
        final Question question = this.questionService.questionById(questionId);
        if (currentUser.usedHelps().stream().anyMatch(help -> help.id().equals(helpToUse.id()))) {
            throw new RuntimeException();
        }
        result.put("question", this.questionService.questionById(question.id()).dtoWithHelp(helpToUse));
        final User savedUser = this.userService.save(currentUser.useHelp(helpToUse, question));
        result.put("user", savedUser.dto());
        this.friendThinks(TIME_FRIEND_THINKS);
        return result;
    }

    private void friendThinks(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

}
