package com.kairosds.technical.test.infrastructure.db.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Price entity.
 */
@Data
@Entity
@Table(name = "PRICES")
@IdClass(PriceEntity.class)
public class PriceEntity implements Serializable {

  /**
   * Product {@link ProductEntity}.
   */
  @Id
  @ManyToOne
  @JoinColumns({
      @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID"),
      @JoinColumn(name = "BRAND_ID", referencedColumnName = "BRAND_ID")
  }
  )
  private ProductEntity product;

  /**
   * Price list.
   */
  @Id
  @Column(name = "PRICE_LIST")
  private Integer priceList;

  /**
   * Start date.
   */
  @Column(name = "START_DATE", nullable = false)
  private LocalDateTime startDate;

  /**
   * End date.
   */
  @Column(name = "END_DATE", nullable = false)
  private LocalDateTime endDate;

  /**
   * Priority.
   */
  @Column(nullable = false)
  private Integer priority;

  /**
   * Price.
   */
  @Column(nullable = false)
  private BigDecimal price;

  /**
   * Currency.
   */
  @Column(name = "CURR", length = 3)
  private String currency;
}
