package com.github.sekkycodes.softwarequest.domain.commands;

import java.util.UUID;

/**
 * Command issued against the software quest domain. Usually an aggregate is targeted by those
 * commands. A command always has a correlation ID and typically will trigger an event when
 * executed.
 */
public interface DomainCommand {

  /**
   * @return an ID that uniquely identifies this command
   */
  UUID getId();

  /**
   * @return the correlation ID associated with this command and any subsequent events
   */
  UUID getCorrelationId();
}
