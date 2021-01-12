package ru.suhan.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.help.HelpName;
import ru.suhan.domain.question.Question;
import ru.suhan.domain.question.QuestionDto;
import ru.suhan.domain.user.User;
import ru.suhan.service.amount.AmountService;
import ru.suhan.service.help.HelpService;
import ru.suhan.service.question.QuestionService;
import ru.suhan.service.user.UserService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/question")
public class QuestionController {

    private final static Long ZERO_AMOUNT_ID = 1L;

    private final QuestionService questionService;
    private final UserService userService;
    private final AmountService amountService;
    private final HelpService helpService;

    @GetMapping
    public QuestionDto nextQuestion() {
        final Amount currentAmount = this.userService.currentUser().currentAmount();
        return this.questionService.questionByLevel(this.amountService.nextAmount(currentAmount)).dto();
    }

    @PutMapping("/{idQuestion:\\d+}/{idAnswer:\\d+}")
    public Map<String, Object> selectAnswer(@PathVariable Long idQuestion, @PathVariable Long idAnswer) {
        final Map<String, Object> result = new HashMap<>();
        final Question question = questionService.questionById(idQuestion);
        result.put("question", question.dtoWithTrueAnswer());
        final User currentUser = userService.currentUser();
        if (currentUser.questionToMistake() != null && currentUser.questionToMistake().id().equals(question.id()) ||
                question.findAnswerById(idAnswer).isTrueAnswer()) {
            final Amount nextAmount = amountService.nextAmount(currentUser.currentAmount());
            if (amountService.isLastAmount(nextAmount)) {
                final User savedUser = userService.save(currentUser.nextLevel(nextAmount).endGame());
                result.put("user", savedUser.dto());
            } else {
                final User savedUser = userService.save(currentUser.nextLevel(nextAmount));
                result.put("user", savedUser.dto());
            }
        } else {
            final User savedUser = userService.save(currentUser.gameOver(this.amountService.findById(ZERO_AMOUNT_ID)));
            result.put("user", savedUser.dto());
        }
        return result;
    }

    @PutMapping("/{idQuestion:\\d+}/activateHelp/{idHelp:\\d+}")
    public Map<String, Object> selectHelp(@PathVariable Long idQuestion, @PathVariable Long idHelp) {
        final Map<String, Object> result = new HashMap<>();
        final User currentUser = userService.currentUser();
        final Help helpToUse = this.helpService.findById(idHelp);
        final Question question = this.questionService.questionById(idQuestion);
        if (currentUser.usedHelps().stream().anyMatch(help -> help.id().equals(helpToUse.id()))) {
            throw new RuntimeException();
        }
        if (helpToUse.name().equals(HelpName.REPLACE.getCodeName())) {
            result.put("question", this.questionService.replaceQuestion(question.id()).dtoWithHelp(helpToUse));
        } else if (helpToUse.name().equals(HelpName.MISTAKES.getCodeName())) {
            result.put("question", question.dto());
        } else {
            result.put("question", this.questionService.questionById(question.id()).dtoWithHelp(helpToUse));
        }
        final User savedUser = this.userService.save(currentUser.useHelp(helpToUse, question));
        result.put("user", savedUser.dto());
        return result;
    }


}
