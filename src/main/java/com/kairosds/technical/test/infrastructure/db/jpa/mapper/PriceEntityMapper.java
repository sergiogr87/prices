package com.kairosds.technical.test.infrastructure.db.jpa.mapper;

import com.kairosds.technical.test.domain.model.Price;
import com.kairosds.technical.test.infrastructure.db.jpa.entity.PriceEntity;
import org.mapstruct.Mapper;

/**
 * Price entity mapper.
 */
@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

  /**
   * Maps the given {@link PriceEntity} to a {@link Price}.
   *
   * @param priceEntity Price entity {@link PriceEntity}.
   * @return Product Price data {@link Price}.
   */
  Price toDomain(final PriceEntity priceEntity);
}