package com.kairosds.technical.test.infrastructure.db.jpa.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Product entity id.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductId implements Serializable {

  /**
   * Id.
   */
  private Integer id;

  /**
   * Brand id.
   */
  private Integer brandId;
}