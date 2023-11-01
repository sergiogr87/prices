package com.kairosds.technical.test.application.usecases;

import com.kairosds.technical.test.domain.model.Price;
import java.time.LocalDateTime;

/**
 * Find Product Price use case.
 */
public interface FindPriceUseCase {

  /**
   * Finds Product Price with the highest priority, which range of dates includes application date.
   *
   * @param brandId Brand id.
   * @param productId Product id.
   * @param applicationDate application date.
   * @return Product Price {@link Price}.
   */
  Price findPrice(final Integer brandId, final Integer productId, final LocalDateTime applicationDate);
}
