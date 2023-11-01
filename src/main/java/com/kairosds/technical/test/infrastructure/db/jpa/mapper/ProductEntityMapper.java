package com.kairosds.technical.test.infrastructure.db.jpa.mapper;

import com.kairosds.technical.test.domain.model.Product;
import com.kairosds.technical.test.infrastructure.db.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;

/**
 * Product entity mapper.
 */
@Mapper(componentModel = "spring")
public interface ProductEntityMapper {

  /**
   * Maps the given {@link Product} to a {@link ProductEntity}.
   *
   * @param product Product data {@link Product}.
   * @return Product entity {@link ProductEntity}.
   */
  ProductEntity toEntity(final Product product);
}