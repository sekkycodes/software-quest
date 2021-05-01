package com.github.sekkycodes.softwarequest.controllers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.Builder;
import lombok.Value;

/**
 * Generic response wrapper to supply responses with additional status and messages
 *
 * @param <T> the original type of object that should be returned and is wrapped by this class
 */
@Value
@Builder(toBuilder = true)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class ControllerResponse<T> {

  /**
   * Response message, typically error messages
   */
  String message;

  /**
   * The body of the response - might be null if an error occurred
   */
  T value;
}
