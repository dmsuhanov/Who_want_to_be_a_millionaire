package ru.suhan.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suhan.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

}
