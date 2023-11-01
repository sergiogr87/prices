package com.kairosds.technical.test.it.rest;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class PriceRestIT {

  private static final String API_PRODUCT_PRICE_PATH = "/brands/{brandId}/products/{productId}/price";
  private static final String PATH_PARAM_BRAND_ID = "{brandId}";
  private static final String PATH_PARAM_PRODUCT_ID = "{productId}";
  private static final String QUERY_PARAM_APPLICATION_DATE = "applicationDate";

  private static final String EUR_CURRENCY = "EUR";

  private static final String UNKNOWN_VALUE = "UNKNOWN";

  private static final Integer BRAND_ID = 1;

  private static final Integer PRODUCT_ID_NOT_FOUND_TEST = 35456;
  private static final String APPLICATION_DATE_NOT_FOUND_TEST = "2020-06-14-10.00.00";

  private static final String BRAND_ID_BAD_REQUEST_TEST = "1";
  private static final String PRODUCT_ID_BAD_REQUEST_TEST = "35455";
  private static final String APPLICATION_DATE_BAD_REQUEST_TEST = "2020-06-14-10.00.00";

  @Autowired
  private MockMvc mockMvc;

  @ParameterizedTest
  @MethodSource("priceTestCasesArguments")
  public void assertPriceDataInResponse(
      final Integer brandId, final Integer productId, final String applicationDate, final Integer priceList,
      final String startDate, final String endDate, final BigDecimal price, final String currency) throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_PRODUCT_PRICE_PATH
                    .replace(PATH_PARAM_BRAND_ID, Integer.toString(brandId))
                    .replace(PATH_PARAM_PRODUCT_ID, Integer.toString(productId)))
                .queryParam(QUERY_PARAM_APPLICATION_DATE, applicationDate))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value(brandId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value(productId))
        .andExpect(MockMvcResultMatchers.jsonPath("$.priceList").value(priceList))
        .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value(startDate))
        .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value(endDate))
        .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(price))
        .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value(currency));
  }

  @Test
  public void assertPriceNotFoundResponse() throws Exception {
    final String brandId = Integer.toString(BRAND_ID);
    final String productId = Integer.toString(PRODUCT_ID_NOT_FOUND_TEST);

    mockMvc.perform(
            MockMvcRequestBuilders.get(API_PRODUCT_PRICE_PATH
                    .replace(PATH_PARAM_BRAND_ID, brandId)
                    .replace(PATH_PARAM_PRODUCT_ID, productId))
                .queryParam(QUERY_PARAM_APPLICATION_DATE, APPLICATION_DATE_NOT_FOUND_TEST))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage")
            .value(
                "Price not found for Product with Brand "
                    .concat(brandId)
                    .concat(" and id ")
                    .concat(productId)
                    .concat(", for date ")
                    .concat(APPLICATION_DATE_NOT_FOUND_TEST)));
  }

  @ParameterizedTest
  @MethodSource("priceTestCasesBadRequestArgumentTypeMismatch")
  public void assertPriceBadRequestArgumentTypeMismatchResponse(
      final String brandId, final String productId, final String applicationDate, final String errorMessage) throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_PRODUCT_PRICE_PATH
                    .replace(PATH_PARAM_BRAND_ID, brandId)
                    .replace(PATH_PARAM_PRODUCT_ID, productId))
                .queryParam(QUERY_PARAM_APPLICATION_DATE, applicationDate))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage").value(errorMessage));
  }

  @Test
  public void assertPriceBadRequestApplicationDateRequestParamMissingResponse() throws Exception {
    mockMvc.perform(
            MockMvcRequestBuilders.get(API_PRODUCT_PRICE_PATH
                .replace(PATH_PARAM_BRAND_ID, BRAND_ID_BAD_REQUEST_TEST)
                .replace(PATH_PARAM_PRODUCT_ID, PRODUCT_ID_BAD_REQUEST_TEST)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.errorMessage")
            .value("Required request parameter 'applicationDate' for method parameter type LocalDateTime is not present"));
  }

  private static Stream<Arguments> priceTestCasesArguments() {
    return Stream.of(
        Arguments.of(BRAND_ID, 35455, "2020-06-14-10.00.00", 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", BigDecimal.valueOf(35.50),
            EUR_CURRENCY),
        Arguments.of(BRAND_ID, 35455, "2020-06-14-16.00.00", 2, "2020-06-14-15.00.00", "2020-06-14-18.30.00", BigDecimal.valueOf(25.45),
            EUR_CURRENCY),
        Arguments.of(BRAND_ID, 35455, "2020-06-14-21.00.00", 1, "2020-06-14-00.00.00", "2020-12-31-23.59.59", BigDecimal.valueOf(35.50),
            EUR_CURRENCY),
        Arguments.of(BRAND_ID, 35455, "2020-06-15-10.00.00", 3, "2020-06-15-00.00.00", "2020-06-15-11.00.00", BigDecimal.valueOf(30.50),
            EUR_CURRENCY),
        Arguments.of(BRAND_ID, 35455, "2020-06-16-21.00.00", 4, "2020-06-15-16.00.00", "2020-12-31-23.59.59", BigDecimal.valueOf(38.95), EUR_CURRENCY)
    );
  }

  private static Stream<Arguments> priceTestCasesBadRequestArgumentTypeMismatch() {
    return Stream.of(
        Arguments.of(UNKNOWN_VALUE, PRODUCT_ID_BAD_REQUEST_TEST, APPLICATION_DATE_BAD_REQUEST_TEST, "brandId is not a valid Integer"),
        Arguments.of(BRAND_ID_BAD_REQUEST_TEST, UNKNOWN_VALUE, APPLICATION_DATE_BAD_REQUEST_TEST, "productId is not a valid Integer"),
        Arguments.of(BRAND_ID_BAD_REQUEST_TEST, PRODUCT_ID_BAD_REQUEST_TEST, UNKNOWN_VALUE, "applicationDate is not a valid LocalDateTime")
    );
  }
}