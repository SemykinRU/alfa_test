package ru.semykin.alfa_test.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.semykin.alfa_test.client.CurrencyClient;
import ru.semykin.alfa_test.client.GiphyClient;
import ru.semykin.alfa_test.dto.CurrencyDto;
import ru.semykin.alfa_test.dto.GiphyDataDto;
import ru.semykin.alfa_test.dto.GiphyDto;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@SpringBootTest
class GetUrlFromGiphyServiceTest {

    @Autowired
    private GetUrlFromGiphyService getUrlFromGiphyService;

    @MockBean
    private CurrencyClient currencyClient;

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private SettingsService settings;

    private GiphyDto giphyDto;
    private GiphyDataDto giphyDataDto;
    private CurrencyDto todayCurrencyDto;
    private CurrencyDto yesterdayCurrencyDto;

    @BeforeEach
    public void setUP() {
        giphyDto = new GiphyDto();
        giphyDataDto = new GiphyDataDto();
        giphyDataDto.setEmbedUrl("link");
        giphyDto.setData(giphyDataDto);
        todayCurrencyDto = new CurrencyDto();
        yesterdayCurrencyDto = new CurrencyDto();
    }

    @AfterEach
    public void tierDown() {
        giphyDto = null;
        giphyDataDto = null;
        todayCurrencyDto = yesterdayCurrencyDto = null;
    }

    @Test
    void whenGetUrlFromGiphyServiceReturnRichUrlOnGifThen() {

        todayCurrencyDto.setRates(Map.of(TEST_UE, 73.456));

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
                        TEST_UE))
                .thenReturn(yesterdayCurrencyDto);

        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        RICH_TAG,
                        RATING))
                .thenReturn(giphyDto);

        var result = getUrlFromGiphyService.responseFromGiphy(TEST_UE);

        assertTrue(result.toString().contains(giphyDataDto.getEmbedUrl()));
    }

    @Test
    void whenGetUrlFromGiphyServiceReturnBrokeUrlOnGifThen() {

        todayCurrencyDto.setRates(Map.of(TEST_UE, 73.456));

        yesterdayCurrencyDto.setRates(Map.of(TEST_UE, 75.00));

        when(currencyClient
                .readCurrencyFromDay(TODAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE))
                .thenReturn(todayCurrencyDto);

        when(currencyClient.
                readCurrencyFromDay(YESTERDAY,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        TEST_UE))
                .thenReturn(yesterdayCurrencyDto);

        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        BROKE_TAG,
                        RATING))
                .thenReturn(giphyDto);

        var result = getUrlFromGiphyService.responseFromGiphy(TEST_UE);

        assertTrue(result.toString().contains(giphyDataDto.getEmbedUrl()));
    }
}