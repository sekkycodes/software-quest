package com.github.sekkycodes.softwarequest.domain.events;

import java.util.UUID;

/**
 * A domain event usually triggered by a command.
 */
public interface DomainEvent {

  /**
   * @return get the ID that uniquely identifies this event
   */
  UUID getId();

  /**
   * @return correlation ID associated with this event
   */
  UUID getCorrelationId();
}
