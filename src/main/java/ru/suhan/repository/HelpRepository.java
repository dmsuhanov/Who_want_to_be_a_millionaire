package ru.suhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suhan.entity.AmountEntity;
import ru.suhan.entity.HelpEntity;

import java.util.Optional;

@Repository
public interface HelpRepository extends JpaRepository<HelpEntity, Long> {

    HelpEntity findHelpEntityByName(String name);

}
