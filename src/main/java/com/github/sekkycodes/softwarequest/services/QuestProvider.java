package com.github.sekkycodes.softwarequest.services;

import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import com.github.sekkycodes.softwarequest.repositories.QuestRepository;
import java.util.Objects;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Find and retrieve Quest aggregates and value objects from repositories
 */
@Service
public class QuestProvider {

  private final QuestRepository questRepository;

  @Autowired
  public QuestProvider(QuestRepository questRepository) {
    this.questRepository = Objects.requireNonNull(questRepository);
  }

  public Optional<QuestVO> findValueById(String id) {
    Optional<Quest> quest = questRepository.findById(new ObjectId(id));
    return quest.map(Quest::toValueObject);
  }
}
