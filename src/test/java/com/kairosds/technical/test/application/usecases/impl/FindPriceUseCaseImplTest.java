package com.kairosds.technical.test.application.usecases.impl;

import com.kairosds.technical.test.domain.exceptions.PriceNotFoundException;
import com.kairosds.technical.test.domain.model.Price;
import com.kairosds.technical.test.domain.model.Product;
import com.kairosds.technical.test.domain.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FindPriceUseCaseImplTest {

  private static final Integer BRAND_ID = 1;
  private static final Integer PRODUCT_ID = 35455;
  private static final LocalDateTime APPLICATION_DATE = LocalDateTime.of(2020, 6, 14, 0, 0);
  private static final Integer LOW_PRIORITY = 0;
  private static final Integer HIGH_PRIORITY = 1;
  private static final Integer HIGHEST_PRIORITY = 2;

  @Mock
  private PriceRepository priceRepository;

  @InjectMocks
  private FindPriceUseCaseImpl findPriceUseCase;

  @Test
  public void givenBrandIdAndProductIdAndApplicationDateWhenFindPriceThenReturnHighPriorityPrice() {
    final LocalDateTime startDate = APPLICATION_DATE.minusDays(1);
    final LocalDateTime endDate = APPLICATION_DATE.plusDays(1);
    final Product product = Product.builder().id(PRODUCT_ID).brandId(BRAND_ID).build();
    final Price lowPriorityPrice = buildPrice(startDate, endDate, LOW_PRIORITY);
    final Price highPriorityPrice = buildPrice(startDate, endDate, HIGH_PRIORITY);
    final Price startDateAfterApplicationDatePrice = buildPrice(APPLICATION_DATE.plusDays(1), APPLICATION_DATE.plusDays(2), HIGHEST_PRIORITY);
    final Price endDateBeforeApplicationDatePrice = buildPrice(APPLICATION_DATE.minusDays(2), APPLICATION_DATE.minusDays(1), HIGHEST_PRIORITY);

    Mockito.when(priceRepository.findPrices(product))
        .thenReturn(List.of(lowPriorityPrice, highPriorityPrice, startDateAfterApplicationDatePrice, endDateBeforeApplicationDatePrice));

    Assertions.assertEquals(highPriorityPrice, findPriceUseCase.findPrice(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
  }

  @Test
  public void givenBrandIdAndProductIdAndApplicationDateWhenFindPriceThenThrowPriceNotFoundException() {
    Assertions.assertThrows(PriceNotFoundException.class,
        () -> findPriceUseCase.findPrice(BRAND_ID, PRODUCT_ID, APPLICATION_DATE));
  }

  private Price buildPrice(final LocalDateTime startDate, final LocalDateTime endDate, final Integer priority) {
    return Price.builder()
        .startDate(startDate)
        .endDate(endDate)
        .priority(priority)
        .build();
  }
}