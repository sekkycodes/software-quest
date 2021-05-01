package com.github.sekkycodes.softwarequest.domain.events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;

/**
 * Event for a newly created quest
 */
@Value
@Builder(toBuilder = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonDeserialize(builder = NewQuestCreated.NewQuestCreatedBuilder.class)
public class NewQuestCreated implements DomainEvent {

  UUID id;
  UUID correlationId;
  UUID aggregateId;
  String questName;

  @JsonPOJOBuilder(withPrefix = "")
  public static class NewQuestCreatedBuilder {

  }
}
