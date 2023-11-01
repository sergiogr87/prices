package com.kairosds.technical.test.domain.repository;

import com.kairosds.technical.test.domain.model.Price;
import com.kairosds.technical.test.domain.model.Product;
import java.util.List;

/**
 * Price repository {@link Price}.
 */
public interface PriceRepository {

  /**
   * Finds Prices by Product.
   *
   * @param product product {@link Product}.
   * @return Prices found by Product {@link Price}.
   */
  List<Price> findPrices(final Product product);
}