package com.kairosds.technical.test.infrastructure.rest.mapper;

import com.kairosds.technical.test.domain.model.Price;
import com.kairosds.technical.test.infrastructure.rest.dto.PriceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Price mapper.
 */
@Mapper(componentModel = "spring")
public interface PriceMapper {

  /**
   * Maps the given {@link Price} to a {@link PriceDto}.
   *
   * @param price Product Price data {@link Price}.
   * @return Price DTO {@link PriceDto}.
   */
  @Mappings({
      @Mapping(target = "productId", source = "product.id"),
      @Mapping(target = "brandId", source = "product.brandId")
  })
  PriceDto toDto(final Price price);
}
