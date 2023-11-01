package com.kairosds.technical.test.infrastructure.rest.exception.handlers;

import com.kairosds.technical.test.domain.exceptions.PriceNotFoundException;
import com.kairosds.technical.test.infrastructure.rest.dto.ErrorDto;
import java.time.OffsetDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Price Rest exception handlers.
 */
@ControllerAdvice
public class PriceRestExceptionHandler {

  /**
   * Handles Price not found exception returning a Not Found response.
   *
   * @param priceNotFoundException Price not found exception {@link PriceNotFoundException}.
   * @return Not Found response.
   */
  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity handlePriceNotFoundException(final PriceNotFoundException priceNotFoundException) {
    return new ResponseEntity(buildErrorBody(priceNotFoundException.getMessage()), HttpStatus.NOT_FOUND);
  }

  /**
   * Handles Method Argument Type Mismatch exception returning a Bad Request response.
   *
   * @param methodArgumentTypeMismatchException Method Argument Type Mismatch exception {@link MethodArgumentTypeMismatchException}.
   * @return Bad Request response.
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
    return new ResponseEntity(buildErrorBody(
        methodArgumentTypeMismatchException.getName()
            .concat(" is not a valid ")
            .concat(methodArgumentTypeMismatchException.getRequiredType().getSimpleName())),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handles Missing Servlet Request Parameter exception returning a Bad Request response.
   *
   * @param missingServletRequestParameterException Missing Servlet Request Parameter exception {@link MissingServletRequestParameterException}.
   * @return Bad Request response.
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity handleMissingServletRequestParameterException(
      final MissingServletRequestParameterException missingServletRequestParameterException) {
    return new ResponseEntity(buildErrorBody(missingServletRequestParameterException.getMessage()), HttpStatus.BAD_REQUEST);
  }

  /**
   * Builds error body.
   *
   * @param errorMessage error message.
   * @return error body {@link ErrorDto}.
   */
  private ErrorDto buildErrorBody(final String errorMessage) {
    return ErrorDto.builder()
        .timestamp(OffsetDateTime.now())
        .errorMessage(errorMessage)
        .build();
  }
}