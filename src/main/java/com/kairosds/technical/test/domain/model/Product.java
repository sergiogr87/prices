package com.kairosds.technical.test.domain.model;


import lombok.Builder;
import lombok.Value;

/**
 * Product data.
 */
@Value
@Builder
public class Product {

  /**
   * Product id.
   */
  Integer id;

  /**
   * Brand id.
   */
  Integer brandId;
}
