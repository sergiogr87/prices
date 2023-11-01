package com.kairosds.technical.test.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

/**
 * Product Price data.
 */
@Value
@Builder
public class Price {

  /**
   * Product {@link Product}.
   */
  Product product;

  /**
   * Start date.
   */
  LocalDateTime startDate;

  /**
   * End date.
   */
  LocalDateTime endDate;

  /**
   * Price list.
   */
  Integer priceList;

  /**
   * Priority.
   */
  Integer priority;

  /**
   * Price.
   */
  BigDecimal price;

  /**
   * Currency.
   */
  String currency;
}
