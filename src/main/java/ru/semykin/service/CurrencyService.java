package main.java.ru.semykin.service;

import org.springframework.stereotype.Service;
import static main.java.ru.semykin.util.ApplicationConstants.*;

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