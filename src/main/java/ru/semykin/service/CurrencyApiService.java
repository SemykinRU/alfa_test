package main.java.ru.semykin.service;

import main.java.ru.semykin.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static main.java.ru.semykin.util.ApplicationConstants.*;

@Service
public class CurrencyApiService {

    private final FeignClientService feignClientService;

    private final SettingsService settings;

    public CurrencyApiService(FeignClientService feignClientService, SettingsService settings) {
        this.feignClientService = feignClientService;
        this.settings = settings;
    }

    public boolean isIncreased(String symbols) {
        CurrencyDto todayCurrency = getCurrencyDto(TODAY, symbols);
        CurrencyDto yesterdayCurrency = getCurrencyDto(YESTERDAY, symbols);
        return todayCurrency.getRates().get(symbols) >= yesterdayCurrency.getRates().get(symbols);
    }

    public CurrencyDto getCurrencyDto(String date, String symbols) {
        return feignClientService
                .getCurrencyApiClient()
                .readCurrencyFromDay(date, settings.getCurrencyAppID(), symbols);
    }
}