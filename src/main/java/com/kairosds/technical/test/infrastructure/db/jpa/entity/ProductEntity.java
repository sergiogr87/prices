package com.kairosds.technical.test.infrastructure.db.jpa.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

/**
 * Product entity.
 */
@Data
@Entity
@Table(name = "PRODUCTS")
@IdClass(ProductId.class)
public class ProductEntity implements Serializable {

  /**
   * Id.
   */
  @Id
  private Integer id;

  /**
   * Brand id.
   */
  @Id
  @Column(name = "BRAND_ID")
  private Integer brandId;
}