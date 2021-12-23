package main.java.ru.semykin.service;

import lombok.Getter;
import lombok.Setter;
import main.java.ru.semykin.client.CurrencyApiClient;
import main.java.ru.semykin.client.GiphyApiClient;
import main.java.ru.semykin.dto.CurrencyDto;
import main.java.ru.semykin.dto.GiphyDto;
import org.springframework.stereotype.Service;

import static main.java.ru.semykin.util.ApplicationConstants.RATING;

@Service
@Getter
@Setter
public class FeignClientService {

    private final CurrencyApiClient currencyApiClient;

    private final GiphyApiClient giphyApiClient;

    private final SettingsService settings;

    public FeignClientService(CurrencyApiClient currencyApiClient, GiphyApiClient giphyApiClient, SettingsService settings) {
        this.currencyApiClient = currencyApiClient;
        this.giphyApiClient = giphyApiClient;
        this.settings = settings;
    }

    public CurrencyDto getCurrencyDto(String date, String symbols) {
       return currencyApiClient.readCurrencyFromDay(date, settings.getCurrencyAppID(), settings.getBaseCurrency(), symbols);
    }

    public GiphyDto getGiphyDto(String tag) {
        return giphyApiClient.getGif(settings.getGiphyApiKey(), tag, RATING);
    }
}
