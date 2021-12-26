package ru.semykin.alfa_test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.semykin.alfa_test.service.GetUrlFromGiphyService;

@RestController
@RequestMapping(value = "/currency")
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
