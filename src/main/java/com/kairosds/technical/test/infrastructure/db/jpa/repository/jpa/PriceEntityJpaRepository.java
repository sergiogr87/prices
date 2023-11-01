package com.kairosds.technical.test.infrastructure.db.jpa.repository.jpa;

import com.kairosds.technical.test.infrastructure.db.jpa.entity.PriceEntity;
import com.kairosds.technical.test.infrastructure.db.jpa.entity.PriceId;
import com.kairosds.technical.test.infrastructure.db.jpa.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Price entity JPA repository {@link PriceEntity}.
 */
public interface PriceEntityJpaRepository extends JpaRepository<PriceEntity, PriceId> {

  /**
   * Finds Prices by Product.
   *
   * @param product Product {@link ProductEntity}.
   * @return Prices found by Product {@link PriceEntity}.
   */
  List<PriceEntity> findByProduct(final ProductEntity product);
}