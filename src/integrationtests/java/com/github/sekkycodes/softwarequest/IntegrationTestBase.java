package com.github.sekkycodes.softwarequest;

import com.github.sekkycodes.softwarequest.domain.DomainDataGenerator;
import com.github.sekkycodes.softwarequest.repositories.QuestRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class IntegrationTestBase {

  @Autowired
  protected QuestRepository questRepository;

  protected DomainDataGenerator domainDataGenerator;

  @BeforeEach
  void beforeEachTestMethod() {
    emptyRepositories();
  }

  @AfterEach
  void afterEachTestMethod() {
    emptyRepositories();
  }

  private void emptyRepositories() {
    domainDataGenerator = new DomainDataGenerator();

    questRepository.deleteAll();
  }
}
