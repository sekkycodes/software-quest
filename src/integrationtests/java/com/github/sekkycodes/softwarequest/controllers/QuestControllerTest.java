package com.github.sekkycodes.softwarequest.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.sekkycodes.softwarequest.IntegrationTestBase;
import com.github.sekkycodes.softwarequest.domain.commands.CreateNewQuest;
import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import com.github.sekkycodes.softwarequest.services.QuestCreator;
import com.github.sekkycodes.softwarequest.services.QuestProvider;
import java.util.Optional;
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

    QuestCreator creator = new QuestCreator(questRepository);
    QuestProvider provider = new QuestProvider(questRepository);

    sut = new QuestController(provider, creator);
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

      QuestVO responseValue = assertAndGetResponseBody(response);
      assertThat(responseValue.getName()).isEqualTo(quest.getName());
    }
  }

  @Nested
  class CreateQuest {

    private CreateNewQuest command;

    @BeforeEach
    void beforeEach() {
      command = CreateNewQuest.builder()
          .name("dummyQuestName")
          .build();
    }

    @Test
    void returnsResponseWithCreatedQuest() {
      ResponseEntity<ControllerResponse<QuestVO>> response = sut.createNewQuest(command);

      QuestVO responseValue = assertAndGetResponseBody(response);
      assertThat(responseValue.getName()).isEqualTo(command.getName());
    }

    @Test
    void persistsNewQuestToRepository() {
      ResponseEntity<ControllerResponse<QuestVO>> response = sut.createNewQuest(command);

      QuestVO responseValue = assertAndGetResponseBody(response);
      Optional<Quest> foundQuest = questRepository.findById(new ObjectId(responseValue.getId()));
      assertThat(foundQuest).isPresent();
      assertThat(foundQuest.get().getName()).isEqualTo(command.getName());
    }
  }

  private <T> T assertAndGetResponseBody(ResponseEntity<ControllerResponse<T>> response) {
    assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getBody()).isNotNull();
    T responseValue = response.getBody().getValue();
    assertThat(responseValue).isNotNull();
    return responseValue;
  }
}
