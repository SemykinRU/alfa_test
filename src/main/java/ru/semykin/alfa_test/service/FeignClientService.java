package ru.semykin.alfa_test.service;

import org.springframework.stereotype.Service;
import ru.semykin.alfa_test.client.CurrencyClient;
import ru.semykin.alfa_test.client.GiphyClient;
import ru.semykin.alfa_test.dto.CurrencyDto;
import ru.semykin.alfa_test.dto.GiphyDto;

import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@Service
public class FeignClientService {

    private final CurrencyClient currencyClient;

    private final GiphyClient giphyClient;

    private final SettingsService settings;


    public FeignClientService(final CurrencyClient currencyClient,
                              final GiphyClient giphyClient,
                              final SettingsService settings) {

        this.currencyClient = currencyClient;
        this.giphyClient = giphyClient;
        this.settings = settings;
    }


    public CurrencyDto getCurrencyDto(final String date, final String symbols) {
        final CurrencyDto currencyDto = currencyClient
                .readCurrencyFromDay(date,
                        settings.getCurrencyAppID(),
                        settings.getBaseCurrency(),
                        symbols);
        if (currencyDto == null) {
            throw new IllegalArgumentException(String.format(FAILED_GET_CURRENCY_DATA, symbols, date));
        }
        return currencyDto;
    }

    public GiphyDto getGiphyDto(String tag) {
        final GiphyDto giphyDto = giphyClient.getGif(settings.getGiphyApiKey(), tag, RATING);
        if (giphyDto == null) {
            throw new IllegalArgumentException(String.format(FAILED_GET_GIF, tag));
        }
        return giphyDto;
    }
}
