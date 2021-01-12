package ru.suhan.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.suhan.domain.ToDto;
import ru.suhan.domain.amount.AmountDto;
import ru.suhan.service.amount.AmountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AmountController {

    private final AmountService amountService;

    @RequestMapping(value = "/amount", method = RequestMethod.GET)
    public List<AmountDto> amountList() {
        return this.amountService.all()
                .stream()
                .filter(a -> a.value() != 0L)
                .map(ToDto::dto)
                .sorted((t1, t2) -> Long.compare(t2.getId(), t1.getId()))
                .collect(Collectors.toList());
    }

}
