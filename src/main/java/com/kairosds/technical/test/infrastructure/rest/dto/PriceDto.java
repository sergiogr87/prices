package com.kairosds.technical.test.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairosds.technical.test.infrastructure.rest.utils.Constants;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Value;

/**
 * Price DTO.
 */
@Value
public class PriceDto {

  /**
   * Product id.
   */
  Integer productId;

  /**
   * Brand id.
   */
  Integer brandId;

  /**
   * Price list.
   */
  Integer priceList;

  /**
   * Start date.
   */
  @JsonFormat(pattern = Constants.DATE_PATTERN)
  LocalDateTime startDate;

  /**
   * End date.
   */
  @JsonFormat(pattern = Constants.DATE_PATTERN)
  LocalDateTime endDate;

  /**
   * Price.
   */
  BigDecimal price;

  /**
   * Currency.
   */
  String currency;
}
