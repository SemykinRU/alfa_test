package ru.semykin.alfa_test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.semykin.alfa_test.dto.CurrencyDto;

@FeignClient(value = "currency-api", url = "${currency.url}")
public interface CurrencyClient {
    @GetMapping(value = "/historical/{date}.json")
    CurrencyDto readCurrencyFromDay(@PathVariable("date") String date,
                                    @RequestParam("app_id") String appID,
                                    @RequestParam("base") String baseCurrency,
                                    @RequestParam("symbols") String symbols);
}

