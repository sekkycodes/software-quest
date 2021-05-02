package com.github.sekkycodes.softwarequest.controllers;

import com.github.sekkycodes.softwarequest.domain.commands.CreateNewQuest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import com.github.sekkycodes.softwarequest.services.QuestCreator;
import com.github.sekkycodes.softwarequest.services.QuestProvider;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoint for requesting quest data and sending quest-related commands
 */
@RestController
@RequestMapping("/api/v1/quest")
public class QuestController {

  private final QuestProvider questProvider;
  private final QuestCreator questCreator;

  @Autowired
  public QuestController(QuestProvider questProvider, QuestCreator questCreator) {
    this.questProvider = Objects.requireNonNull(questProvider);
    this.questCreator = Objects.requireNonNull(questCreator);
  }

  @GetMapping("byId")
  public ResponseEntity<ControllerResponse<QuestVO>> retrieveQuestById(@RequestParam String id) {
    Optional<QuestVO> optionalQuest = questProvider.findValueById(id);

    if (optionalQuest.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(
        ControllerResponse
            .<QuestVO>builder()
            .value(optionalQuest.get())
            .build());
  }

  @PostMapping("createNewQuest")
  public ResponseEntity<ControllerResponse<QuestVO>> createNewQuest(
      @RequestBody CreateNewQuest command) {

    QuestVO vo = questCreator.create(command);

    return ResponseEntity.ok(
        ControllerResponse
            .<QuestVO>builder()
            .value(vo)
            .build());
  }
}
