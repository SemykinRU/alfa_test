package ru.semykin.alfa_test.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.semykin.alfa_test.dto.CurrencyDto;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@SpringBootTest
class CurrencyServiceTest {

    @MockBean
    CurrencyService currencyService;

    @MockBean
    FeignClientService feignClientService;
    CurrencyDto todayCurrencyDto = new CurrencyDto();
    CurrencyDto yesterdayCurrencyDto = new CurrencyDto();
    Map<String, Double> todayRates = new HashMap<>();
    Map<String, Double> yesterdayRates = new HashMap<>();


    @BeforeEach
    public void setUp() {
        Double todayValue = 73.456;
        Double yesterdayValue = 73.00;
        todayRates.put(TEST_UE, todayValue);
        yesterdayRates.put(TEST_UE, yesterdayValue);
        todayCurrencyDto.setRates(todayRates);
        yesterdayCurrencyDto.setRates(yesterdayRates);
    }

    @AfterEach
    public void tierDown() {
        todayCurrencyDto = yesterdayCurrencyDto = null;
        todayRates = yesterdayRates = null;
    }

    @Test
    void whenToDayCurrencyRateHigherYesterdayRateThenTrue() {
        when(feignClientService.getCurrencyDto(TODAY, TEST_UE))
                .thenReturn(todayCurrencyDto);
        when(feignClientService.getCurrencyDto(YESTERDAY, TEST_UE))
                .thenReturn(todayCurrencyDto);
        when(currencyService.isIncreased(TEST_UE))
                .thenReturn(todayRates.get(TEST_UE) > yesterdayRates.get(TEST_UE));
        assertTrue(currencyService.isIncreased(TEST_UE));
    }

    @Test
    void whenToDayCurrencyRateLowerYesterdayRateThenFalse() {
        when(feignClientService.getCurrencyDto(TODAY, TEST_UE))
                .thenReturn(todayCurrencyDto);
        when(feignClientService.getCurrencyDto(YESTERDAY, TEST_UE))
                .thenReturn(todayCurrencyDto);
        when(currencyService.isIncreased(TEST_UE))
                .thenReturn(todayRates.get(TEST_UE) < yesterdayRates.get(TEST_UE));
        assertFalse(currencyService.isIncreased(TEST_UE));
    }
}
