package main.java.ru.semykin.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Setter
@ToString
@Service
public class SettingsService {

    @Value("${currency.url}")
    private String currencyUrl;

    @Value("${currency.app_id}")
    private String currencyAppID;

    @Value("${giphy.url}")
    private String giphyUrl;

    @Value("${giphy.api_key}")
    private String giphyApiKey;

}
