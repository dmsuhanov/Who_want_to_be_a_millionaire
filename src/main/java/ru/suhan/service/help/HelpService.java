package ru.suhan.service.help;


import ru.suhan.domain.help.Help;

import java.util.List;

public interface HelpService {

    List<Help> all();

    Help findById(Long id);

    Help findByName(String name);

}
