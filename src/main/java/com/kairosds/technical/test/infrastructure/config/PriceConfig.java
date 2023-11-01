package com.kairosds.technical.test.infrastructure.config;

import com.kairosds.technical.test.application.usecases.FindPriceUseCase;
import com.kairosds.technical.test.application.usecases.impl.FindPriceUseCaseImpl;
import com.kairosds.technical.test.domain.repository.PriceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Prices configuration.
 */
@Configuration
public class PriceConfig {

  /**
   * Find Product Price use case.
   *
   * @param priceRepository Price repository {@link PriceRepository}.
   * @return Find Product Price use case {@link FindPriceUseCase}.
   */
  @Bean
  public FindPriceUseCase findPriceUseCase(final PriceRepository priceRepository) {
    return new FindPriceUseCaseImpl(priceRepository);
  }
}
