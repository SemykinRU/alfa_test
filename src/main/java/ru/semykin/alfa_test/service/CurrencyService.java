package ru.semykin.alfa_test.service;

import org.springframework.stereotype.Service;
import ru.semykin.alfa_test.dto.CurrencyDto;

import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@Service
public class CurrencyService {

    private final FeignClientService feignClientService;

    public CurrencyService(final FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public boolean isIncreased(final String symbols) {
        final Double todayValue = getRatesValue(TODAY, symbols);
        final Double yesterdayValue = getRatesValue(YESTERDAY, symbols);
        return todayValue >= yesterdayValue;
    }

    private Double getRatesValue(final String date, final String symbols) {
        final CurrencyDto dto = feignClientService.getCurrencyDto(date, symbols);
        if (dto.getRates() == null || dto.getRates().isEmpty()) {
            throw new IllegalArgumentException(
                    String.format(FAILED_GET_CURRENCY_DATA, symbols, date));
        }
        return dto.getRates().get(symbols);
    }

}