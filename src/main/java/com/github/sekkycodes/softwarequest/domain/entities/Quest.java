package com.github.sekkycodes.softwarequest.domain.entities;

import com.github.sekkycodes.softwarequest.domain.vos.QuestVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Aggregate object
 */
@Data
@Document("quest")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Quest implements DomainAggregate {

  private ObjectId id;

  private String name;

  public QuestVO toValueObject() {
    return QuestVO.builder()
        .id(getId().toString())
        .name(getName())
        .build();
  }
}
