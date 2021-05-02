package com.github.sekkycodes.softwarequest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class QuestTest {

  private Quest sut;

  @BeforeEach
  void beforeEach() {
    DomainDataGenerator dataGenerator = new DomainDataGenerator();
    sut = dataGenerator.entities().buildQuest();
  }

  @Nested
  class ToValueObject {

    @Test
    void convertsEntityToValueObject() {
      QuestVO vo = sut.toValueObject();

      assertThat(sut.getId()).hasToString(vo.getId());
      assertThat(sut.getName()).isEqualTo(vo.getName());
    }
  }
}
