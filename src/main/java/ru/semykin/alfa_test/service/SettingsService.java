package ru.semykin.alfa_test.service;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class SettingsService {

    @Value("${currency.url}")
    private String currencyUrl;

    @Value("${currency.app_id}")
    private String currencyAppID;

    @Value("${currency.base_currency}")
    private String baseCurrency;

    @Value("${giphy.url}")
    private String giphyUrl;

    @Value("${giphy.api_key}")
    private String giphyApiKey;

}
