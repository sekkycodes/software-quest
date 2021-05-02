package com.github.sekkycodes.softwarequest.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.sekkycodes.softwarequest.IntegrationTestBase;
import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class QuestControllerTest extends IntegrationTestBase {

  private QuestController sut;
  private Quest quest;

  @BeforeEach
  void beforeEach() {
    quest = domainDataGenerator.entities().buildQuest();
    questRepository.save(quest);

    sut = new QuestController(questRepository);
  }

  @Nested
  class RetrieveQuest {

    @Test
    void returnsNotFoundForNonExistentEntityId() {
      ResponseEntity<ControllerResponse<QuestVO>> response = sut
          .retrieveQuestById(ObjectId.get().toString());

      assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void returnsResponseWithFoundQuest() {
      ResponseEntity<ControllerResponse<QuestVO>> response = sut
          .retrieveQuestById(quest.getId().toString());

      assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
      assertThat(response.getBody()).isNotNull();
      assertThat(response.getBody().getValue().getName()).isEqualTo(quest.getName());

    }
  }
}
