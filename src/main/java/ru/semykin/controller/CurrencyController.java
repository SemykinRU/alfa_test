package main.java.ru.semykin.controller;

import main.java.ru.semykin.service.CurrencyApiService;
import main.java.ru.semykin.service.GiphyApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class CurrencyController {
   private final CurrencyApiService currencyApiService;
   private final GiphyApiService giphyApiService;

    public CurrencyController(CurrencyApiService currencyApiService, GiphyApiService giphyApiService) {
        this.currencyApiService = currencyApiService;
        this.giphyApiService = giphyApiService;
    }

    @GetMapping(value = "{symbols}")
    public ResponseEntity<String> isTrue(@PathVariable String symbols) {
        String url = giphyApiService.getGifUrl(currencyApiService.isIncreased(symbols));
        String result = String.format("<iframe src='%s'></iframe>", url);
        return ResponseEntity.ok(result);
    }
}
