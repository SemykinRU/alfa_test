package main.java.ru.semykin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "openexchangerates-api", url = "https://openexchangerates.org/api/")
public interface CurrencyApiClient {
    @GetMapping(value = "/historical/{date}.json")
    String readToDayCurrency(@PathVariable("date") String toDay,
                             @RequestParam("app_id") String appID,
                             @RequestParam("symbols") String symbols);
}

