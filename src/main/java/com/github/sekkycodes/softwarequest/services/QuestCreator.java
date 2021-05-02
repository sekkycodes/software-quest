package com.github.sekkycodes.softwarequest.services;

import com.github.sekkycodes.softwarequest.domain.commands.CreateNewQuest;
import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import com.github.sekkycodes.softwarequest.repositories.QuestRepository;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Creates new Quest aggregates
 */
@Service
public class QuestCreator {

  private QuestRepository questRepository;

  @Autowired
  public QuestCreator(QuestRepository questRepository) {
    this.questRepository = Objects.requireNonNull(questRepository);
  }

  public QuestVO create(CreateNewQuest command) {
    Quest quest = Quest.builder()
        .name(command.getName())
        .build();

    return questRepository.save(quest).toValueObject();
  }
}
