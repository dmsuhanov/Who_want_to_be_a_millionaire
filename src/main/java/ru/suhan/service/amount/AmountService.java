package ru.suhan.service.amount;


import ru.suhan.domain.amount.Amount;

import java.util.List;

public interface AmountService {

    List<Amount> all();

    Amount findById(Long id);

    Amount nextAmount(Amount currentAmount);

    boolean isLastAmount(Amount amount);

}
