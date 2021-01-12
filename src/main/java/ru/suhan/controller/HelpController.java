package ru.suhan.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.suhan.domain.ToDto;
import ru.suhan.domain.amount.AmountDto;
import ru.suhan.domain.help.HelpDto;
import ru.suhan.service.amount.AmountService;
import ru.suhan.service.help.HelpService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/help")
public class HelpController {

    private final HelpService helpService;

    @GetMapping
    public List<HelpDto> helpList() {
        return this.helpService.all().stream().map(ToDto::dto).collect(Collectors.toList());
    }

}
