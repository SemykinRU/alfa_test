package main.java.ru.semykin.controller;

import main.java.ru.semykin.service.GetUrlFromGiphyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
public class CurrencyController {

    private final GetUrlFromGiphyService getUrlFromGiphyService;

    public CurrencyController(GetUrlFromGiphyService getUrlFromGiphyService) {
        this.getUrlFromGiphyService = getUrlFromGiphyService;
    }

    @GetMapping(value = "{symbols}")
    public ResponseEntity<String> checkCurses(@PathVariable String symbols) {
        return getUrlFromGiphyService.responseFromGiphy(symbols);
    }
}
