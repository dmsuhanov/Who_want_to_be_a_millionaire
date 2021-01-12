package ru.suhan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suhan.entity.AnswerEntity;
import ru.suhan.entity.TokenEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    List<AnswerEntity> findAllByQuestionEntityId(Long id);

}
