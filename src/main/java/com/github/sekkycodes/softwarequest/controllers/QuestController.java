package com.github.sekkycodes.softwarequest.controllers;

import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import com.github.sekkycodes.softwarequest.repositories.QuestRepository;
import java.util.Objects;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint for requesting quest data and sending quest-related commands
 */
@RestController
@RequestMapping("/api/v1/quest")
public class QuestController {

  private final QuestRepository questRepository;

  @Autowired
  public QuestController(QuestRepository questRepository) {
    this.questRepository = Objects.requireNonNull(questRepository);
  }

  @GetMapping
  public ResponseEntity<ControllerResponse<QuestVO>> retrieveQuestById(@RequestParam String id) {
    Optional<Quest> optionalQuest = questRepository.findById(new ObjectId(id));

    if (optionalQuest.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    QuestVO vo = optionalQuest.get().toValueObject();
    return ResponseEntity.ok(
        ControllerResponse
            .<QuestVO>builder()
            .value(vo)
            .build());
  }
}
