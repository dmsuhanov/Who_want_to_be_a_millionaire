package ru.suhan.domain.amount;


import ru.suhan.entity.AmountEntity;

public class AmountBase implements Amount {

    private final Long id;
    private final Long value;

    public AmountBase(AmountEntity amountEntity) {
        this(amountEntity.getId(), amountEntity.getValue());
    }

    public AmountBase(Long id, Long value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public Long value() {
        return this.value;
    }

    @Override
    public Boolean isExist() {
        return true;
    }

    @Override
    public AmountDto dto() {
        return new AmountDto(this.id, this.value);
    }


}
