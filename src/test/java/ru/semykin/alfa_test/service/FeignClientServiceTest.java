package ru.semykin.alfa_test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.semykin.alfa_test.client.CurrencyClient;
import ru.semykin.alfa_test.client.GiphyClient;
import ru.semykin.alfa_test.dto.CurrencyDto;
import ru.semykin.alfa_test.dto.GiphyDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;
import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@SpringBootTest
class FeignClientServiceTest {

    @MockBean
    CurrencyClient currencyClient;

    @MockBean
    GiphyClient giphyClient;

    @Autowired
    SettingsService settings;

    @Autowired
    FeignClientService feignClientService;


    @Test
    void whenReturnNewCurrencyDtoThen() {
        when(currencyClient
                .readCurrencyFromDay(TODAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE))
                .thenReturn(new CurrencyDto());
        assertThat(feignClientService.getCurrencyDto(TODAY, TEST_UE), is(new CurrencyDto()));
    }

    @Test
    void whenNoReturnNewCurrencyDtoThenNull() {
        when(currencyClient
                .readCurrencyFromDay(TODAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE))
                .thenReturn(null);
        assertNull(feignClientService.getCurrencyDto(TODAY, TEST_UE));
    }

    @Test
    void whenReturnNewGiphyDtoThen() {
        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        RICH_TAG,
                        RATING))
                .thenReturn(new GiphyDto());
        assertThat(feignClientService.getGiphyDto(RICH_TAG), is(new GiphyDto()));
    }

    @Test
    void whenNoReturnNewGiphyDtoThenNull() {
        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        RICH_TAG,
                        RATING))
                .thenReturn(null);
        assertNull(feignClientService.getGiphyDto(RICH_TAG));
    }
}