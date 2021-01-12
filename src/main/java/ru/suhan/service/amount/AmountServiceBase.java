package ru.suhan.service.amount;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.suhan.domain.amount.Amount;
import ru.suhan.domain.amount.AmountBase;
import ru.suhan.repository.AmountRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AmountServiceBase implements AmountService {

    private final static Long STEP_LEVEL = 1L;

    private final AmountRepository amountRepository;

    @Cacheable("amounts")
    @Override
    public List<Amount> all() {
        return this.amountRepository.findAll()
                .stream()
                .map(AmountBase::new)
                .collect(Collectors.toList());
    }

    @Override
    public Amount findById(Long id) {
        return this.all()
                .stream()
                .filter(amount -> amount.id().equals(id))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Amount nextAmount(Amount currentAmount) {
        return this.findById(currentAmount.id() + STEP_LEVEL);
    }

    @Override
    public boolean isLastAmount(Amount amount) {
        final Long maxLevel = this.all()
                .stream()
                .map(Amount::id)
                .max(Long::compare)
                .orElseThrow(RuntimeException::new);
        return amount.id().equals(maxLevel);
    }
}
