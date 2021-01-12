package ru.suhan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.suhan.entity.FriendEntity;

import java.util.List;

public interface FriendRepository extends JpaRepository<FriendEntity, Long> {

    List<FriendEntity> findAllByUserEntityId(Long id);

}
