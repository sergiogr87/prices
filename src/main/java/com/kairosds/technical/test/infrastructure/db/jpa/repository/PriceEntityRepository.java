package com.kairosds.technical.test.infrastructure.db.jpa.repository;

import com.kairosds.technical.test.domain.model.Price;
import com.kairosds.technical.test.domain.model.Product;
import com.kairosds.technical.test.domain.repository.PriceRepository;
import com.kairosds.technical.test.infrastructure.db.jpa.entity.PriceEntity;
import com.kairosds.technical.test.infrastructure.db.jpa.mapper.PriceEntityMapper;
import com.kairosds.technical.test.infrastructure.db.jpa.mapper.ProductEntityMapper;
import com.kairosds.technical.test.infrastructure.db.jpa.repository.jpa.PriceEntityJpaRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * Price entity repository {@link PriceEntity}.
 */
@RequiredArgsConstructor
@Repository
public class PriceEntityRepository implements PriceRepository {

  /**
   * Price entity JPA repository {@link PriceEntityJpaRepository}.
   */
  private final PriceEntityJpaRepository priceEntityJpaRepository;

  /**
   * Product entity mapper {@link ProductEntityMapper}.
   */
  private final ProductEntityMapper productEntityMapper;

  /**
   * Price entity mapper {@link PriceEntityMapper}.
   */
  private final PriceEntityMapper priceEntityMapper;

  @Override
  public List<Price> findPrices(final Product product) {
    return priceEntityJpaRepository.findByProduct(productEntityMapper.toEntity(product))
        .stream()
        .map(priceEntityMapper::toDomain)
        .collect(Collectors.toList());
  }
}