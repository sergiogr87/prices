package com.kairosds.technical.test.domain.exceptions;

/**
 * Price not found exception.
 */
public class PriceNotFoundException extends RuntimeException {

  /**
   * Constructor.
   *
   * @param message exception message.
   */
  public PriceNotFoundException(final String message) {
    super(message);
  }
}
