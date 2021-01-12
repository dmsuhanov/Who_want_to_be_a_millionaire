package ru.suhan.domain.help;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HelpDto {

    private final Long id;
    private final String name;
    private final String description;
}
