package ru.suhan.domain.amount;


import ru.suhan.domain.IsExist;
import ru.suhan.domain.ToDto;

public interface Amount extends ToDto<AmountDto>, IsExist {

    Long id();

    Long value();

}
