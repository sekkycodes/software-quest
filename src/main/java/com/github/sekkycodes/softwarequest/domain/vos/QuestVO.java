package com.github.sekkycodes.softwarequest.domain.vos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

/**
 * Immutable value representation of Quest aggregate
 */
@Value
@Builder(toBuilder = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonDeserialize(builder = QuestVO.QuestVOBuilder.class)
public class QuestVO {

  String id;

  @JsonPOJOBuilder(withPrefix = "")
  public static class QuestVOBuilder {
  }
}
