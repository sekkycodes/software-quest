package com.github.sekkycodes.softwarequest.domain;

import com.github.sekkycodes.softwarequest.domain.entities.Quest;
import org.bson.types.ObjectId;

public class DomainDataGenerator {

  private final Entities entities;

  public DomainDataGenerator() {
    entities = new Entities();
  }

  public Entities entities() {
    return entities;
  }

  public static class Entities {

    public Quest buildQuest() {
      return Quest.builder()
          .id(ObjectId.get())
          .name("dummyQuest")
          .build();
    }
  }
}
