package ru.suhan.domain.help;


import ru.suhan.entity.HelpEntity;

public class HelpBase implements Help {

    private final Long id;
    private final String name;
    private final String description;

    public HelpBase(HelpEntity helpEntity) {
        this(helpEntity.getId(), helpEntity.getName(), helpEntity.getDescription());
    }

    public HelpBase(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String description() {
        return this.description;
    }

    @Override
    public Boolean isExist() {
        return true;
    }

    @Override
    public HelpDto dto() {
        return new HelpDto(this.id, this.name, this.description);
    }
}
