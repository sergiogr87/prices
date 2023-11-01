package com.kairosds.technical.test.application.usecases.impl;

import com.kairosds.technical.test.application.usecases.FindPriceUseCase;
import com.kairosds.technical.test.application.utils.Constants;
import com.kairosds.technical.test.domain.exceptions.PriceNotFoundException;
import com.kairosds.technical.test.domain.model.Price;
import com.kairosds.technical.test.domain.model.Product;
import com.kairosds.technical.test.domain.repository.PriceRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Find Product Price use case impl.
 */
@RequiredArgsConstructor
@Slf4j
public class FindPriceUseCaseImpl implements FindPriceUseCase {

  /**
   * Price repository {@link PriceRepository}.
   */
  private final PriceRepository priceRepository;

  @Override
  public Price findPrice(final Integer brandId, final Integer productId, final LocalDateTime applicationDate) {
    log.info("Find Price for Product with Brand {} and id {}, for date {}", brandId, productId, applicationDate);

    final Product product = Product.builder()
        .id(productId)
        .brandId(brandId)
        .build();

    // Price which range of dates includes application date, with the highest priority is returned
    return priceRepository.findPrices(product)
        .stream()
        .filter(price -> !price.getStartDate().isAfter(applicationDate) && !price.getEndDate().isBefore(applicationDate))
        .max(Comparator.comparing(Price::getPriority))
        .orElseThrow(() -> new PriceNotFoundException(
            "Price not found for Product with Brand "
                .concat(Integer.toString(brandId))
                .concat(" and id ")
                .concat(Integer.toString(productId))
                .concat(", for date ")
                .concat(applicationDate.format(DateTimeFormatter.ofPattern(Constants.DATE_PATTERN)))));
  }
}
