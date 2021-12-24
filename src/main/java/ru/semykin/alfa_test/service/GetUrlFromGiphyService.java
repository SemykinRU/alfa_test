package ru.semykin.alfa_test.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GetUrlFromGiphyService {

    private final CurrencyService currencyService;

    private final GiphyService giphyService;

    public GetUrlFromGiphyService(CurrencyService currencyService, GiphyService giphyService) {
        this.currencyService = currencyService;
        this.giphyService = giphyService;
    }

    public ResponseEntity<String> responseFromGiphy(String symbol) {
        boolean isTrue = currencyService.isIncreased(symbol);
        return ResponseEntity.ok(giphyService.getGifUrl(isTrue));
    }
}
