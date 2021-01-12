package ru.suhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suhan.entity.AmountEntity;
import ru.suhan.entity.UserEntity;

import java.util.Optional;

@Repository
public interface AmountRepository extends JpaRepository<AmountEntity, Long> {

    Optional<AmountEntity> findOneByValue(Long value);

}
