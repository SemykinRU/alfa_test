package ru.semykin.alfa_test.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.semykin.alfa_test.client.CurrencyClient;
import ru.semykin.alfa_test.dto.CurrencyDto;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@SpringBootTest
class CurrencyServiceTest {

    @MockBean
    CurrencyClient currencyClient;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    FeignClientService feignClientService;

    @Autowired
    SettingsService settings;

    CurrencyDto todayCurrencyDto;
    CurrencyDto yesterdayCurrencyDto;

    @BeforeEach
    public void setUp() {
        todayCurrencyDto = new CurrencyDto();
        yesterdayCurrencyDto = new CurrencyDto();
    }

    @AfterEach
    public void tierDown() {
        todayCurrencyDto = yesterdayCurrencyDto = null;
    }

    @Test
    void whenToDayCurrencyRateHigherYesterdayRateThenTrue() {

        todayCurrencyDto.setRates(Map.of(TEST_UE, 75.00));

        yesterdayCurrencyDto.setRates(Map.of(TEST_UE, 70.00));

        when(currencyClient
                .readCurrencyFromDay(TODAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE))
                .thenReturn(todayCurrencyDto);

        when(currencyClient
                .readCurrencyFromDay(YESTERDAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE ))
                .thenReturn(yesterdayCurrencyDto);

        assertTrue(currencyService.isIncreased(TEST_UE));
    }

    @Test
    void whenToDayCurrencyRateLowerYesterdayRateThenFalse() {

        todayCurrencyDto.setRates(Map.of(TEST_UE, 70.00));

        yesterdayCurrencyDto.setRates(Map.of(TEST_UE, 75.00));

        when(currencyClient
                .readCurrencyFromDay(TODAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE))
                .thenReturn(todayCurrencyDto);

        when(currencyClient
                .readCurrencyFromDay(YESTERDAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE ))
                .thenReturn(yesterdayCurrencyDto);

        assertFalse(currencyService.isIncreased(TEST_UE));
    }
}
