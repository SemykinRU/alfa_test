package main.java.ru.semykin.client;

import main.java.ru.semykin.dto.CurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "currency-api", url = "${currency.url}")
public interface CurrencyApiClient {
    @GetMapping(value = "/historical/{date}.json")
    CurrencyDto readCurrencyFromDay(@PathVariable("date") String date,
                                    @RequestParam("app_id") String appID,
                                    @RequestParam("base") String baseCurrency,
                                    @RequestParam("symbols") String symbols);
}

