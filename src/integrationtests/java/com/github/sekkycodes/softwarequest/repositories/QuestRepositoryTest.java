package com.github.sekkycodes.softwarequest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.sekkycodes.softwarequest.IntegrationTestBase;
import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class QuestRepositoryTest extends IntegrationTestBase {

  @Test
  void savesQuestAggregateToDatabase() {
    Quest quest = questRepository.save(domainDataGenerator.entities().buildQuest());

    Optional<Quest> retrievedQuest = questRepository.findById(quest.getId());

    assertThat(retrievedQuest).isPresent();
  }
}
