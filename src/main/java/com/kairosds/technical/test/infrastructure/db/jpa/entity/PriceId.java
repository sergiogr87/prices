package com.kairosds.technical.test.infrastructure.db.jpa.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Price entity id.
 */
@Data
public class PriceId implements Serializable {

  /**
   * Product {@link ProductEntity}.
   */
  private ProductEntity product;

  /**
   * Price list.
   */
  private Integer priceList;
}