package ru.semykin.alfa_test.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.semykin.alfa_test.client.CurrencyClient;
import ru.semykin.alfa_test.client.GiphyClient;
import ru.semykin.alfa_test.dto.CurrencyDto;
import ru.semykin.alfa_test.dto.GiphyDto;
import static ru.semykin.alfa_test.util.ApplicationConstants.RATING;

@Service
@Getter
@Setter
public class FeignClientService {

    private final CurrencyClient currencyClient;

    private final GiphyClient giphyClient;

    private final SettingsService settings;

    public FeignClientService(CurrencyClient currencyClient, GiphyClient giphyClient, SettingsService settings) {
        this.currencyClient = currencyClient;
        this.giphyClient = giphyClient;
        this.settings = settings;
    }

    public CurrencyDto getCurrencyDto(String date, String symbols) {
       return currencyClient.readCurrencyFromDay(date, settings.getCurrencyAppID(), settings.getBaseCurrency(), symbols);
    }

    public GiphyDto getGiphyDto(String tag) {
        return giphyClient.getGif(settings.getGiphyApiKey(), tag, RATING);
    }
}
