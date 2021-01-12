package ru.suhan.domain.amount;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AmountDto {

    private final Long id;
    private final Long value;

}
