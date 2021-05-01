package com.github.sekkycodes.softwarequest.domain.commands;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/**
 * Command for creating a new quest
 */
@Value
@Builder(toBuilder = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonDeserialize(builder = CreateNewQuest.CreateNewQuestBuilder.class)
public class CreateNewQuest implements DomainCommand {

  UUID id;
  UUID correlationId;
  String name;

  @JsonPOJOBuilder(withPrefix = "")
  public static class CreateNewQuestBuilder {

  }

}
