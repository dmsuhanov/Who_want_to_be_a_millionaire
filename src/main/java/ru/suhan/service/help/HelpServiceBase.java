package ru.suhan.service.help;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.suhan.domain.help.Help;
import ru.suhan.domain.help.HelpBase;
import ru.suhan.repository.HelpRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HelpServiceBase implements HelpService {

    private final HelpRepository helpRepository;

    @Override
    public List<Help> all() {
        return this.helpRepository.findAll().stream().map(HelpBase::new).collect(Collectors.toList());
    }

    @Override
    public Help findById(Long id) {
        return new HelpBase(this.helpRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public Help findByName(String name) {
        return new HelpBase(this.helpRepository.findHelpEntityByName(name));
    }

}
