package com.kairosds.technical.test.infrastructure.rest.controller;

import com.kairosds.technical.test.application.usecases.FindPriceUseCase;
import com.kairosds.technical.test.infrastructure.rest.dto.PriceDto;
import com.kairosds.technical.test.infrastructure.rest.mapper.PriceMapper;
import com.kairosds.technical.test.infrastructure.rest.utils.Constants;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Price Rest controller.
 */
@RequiredArgsConstructor
@RestController
public class PriceRestController {

  /**
   * Find Product Price API path.
   */
  private static final String API_PRODUCT_PRICE_PATH = "/brands/{brandId}/products/{productId}/price";

  /**
   * Find Product Price use case {@link FindPriceUseCase}.
   */
  private final FindPriceUseCase findPriceUseCase;

  /**
   * Price mapper {@link PriceMapper}.
   */
  private final PriceMapper priceMapper;

  /**
   * Find Product Price API.
   *
   * @param brandId Brand id.
   * @param productId Product id.
   * @param applicationDate application date.
   * @return Product Price if found, or no content response otherwise {@link PriceDto}.
   */
  @GetMapping(path = API_PRODUCT_PRICE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
  public PriceDto findPrice(
      @PathVariable final Integer brandId,
      @PathVariable final Integer productId,
      @RequestParam @DateTimeFormat(pattern = Constants.DATE_PATTERN) final LocalDateTime applicationDate) {
    return priceMapper.toDto(findPriceUseCase.findPrice(brandId, productId, applicationDate));
  }
}