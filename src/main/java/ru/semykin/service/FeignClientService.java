package main.java.ru.semykin.service;

import lombok.Getter;
import lombok.Setter;
import main.java.ru.semykin.client.CurrencyApiClient;
import main.java.ru.semykin.client.GiphyApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class FeignClientService {

    @Autowired
    private final CurrencyApiClient currencyApiClient;

    @Autowired
    private final GiphyApiClient giphyApiClient;


    public FeignClientService(CurrencyApiClient currencyApiClient, GiphyApiClient giphyApiClient) {
        this.currencyApiClient = currencyApiClient;
        this.giphyApiClient = giphyApiClient;
    }
}
