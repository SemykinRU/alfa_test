package ru.semykin.alfa_test.service;

import org.springframework.stereotype.Service;
import static ru.semykin.alfa_test.util.ApplicationConstants.TODAY;
import static ru.semykin.alfa_test.util.ApplicationConstants.YESTERDAY;

@Service
public class CurrencyService {

    private final FeignClientService feignClientService;

    public CurrencyService(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public boolean isIncreased(String symbols) {
        Double todayValue = feignClientService.getCurrencyDto(TODAY, symbols)
                .getRates()
                .getOrDefault(symbols, 0.0);
        Double yesterdayValue = feignClientService.getCurrencyDto(YESTERDAY, symbols)
                .getRates()
                .getOrDefault(symbols, 0.0);
        return  todayValue >= yesterdayValue;
    }
}