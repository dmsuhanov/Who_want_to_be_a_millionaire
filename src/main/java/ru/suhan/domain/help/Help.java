package ru.suhan.domain.help;


import ru.suhan.domain.IsExist;
import ru.suhan.domain.ToDto;

public interface Help extends ToDto<HelpDto>, IsExist {

    Long id();

    String name();

    String description();

}
