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
    public ResponseEntity<String> checkCurses(@PathVariable String symbols) {
        return ResponseEntity.ok(giphyApiService.getGifUrl(currencyApiService.isIncreased(symbols)));
    }
}
