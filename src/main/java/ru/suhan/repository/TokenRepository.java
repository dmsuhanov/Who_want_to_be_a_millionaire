package ru.suhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.suhan.entity.TokenEntity;
import ru.suhan.entity.UserEntity;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

    Optional<TokenEntity> findOneByValue(String value);

    Optional<TokenEntity> findOneByUserEntityId(Long id);

}
