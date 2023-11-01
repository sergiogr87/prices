package com.kairosds.technical.test.infrastructure.rest.dto;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Value;

/**
 * Error DTO.
 */
@Value
@Builder
public class ErrorDto {

  /**
   * Timestamp.
   */
  OffsetDateTime timestamp;

  /**
   * Error message.
   */
  String errorMessage;
}
