package ru.semykin.alfa_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.semykin.alfa_test.service.GetUrlFromGiphyService;

@RestController
@RequestMapping(value = "/")
public class CurrencyController {

    private final GetUrlFromGiphyService getUrlFromGiphyService;

    public CurrencyController(GetUrlFromGiphyService getUrlFromGiphyService) {
        this.getUrlFromGiphyService = getUrlFromGiphyService;
    }

    @GetMapping(value = "{symbols}")
    public ResponseEntity<String> getUrlFromGiphyByCurrencyRatesStatus(@PathVariable String symbols) {
        return getUrlFromGiphyService.responseFromGiphy(symbols);
    }
}
