package main.java.ru.semykin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static main.java.ru.semykin.util.ApplicationConstants.*;

@Service
public class GiphyApiService {

    @Autowired
    private final FeignClientService feignClientService;

    @Autowired
    private final SettingsService settings;

    public GiphyApiService(FeignClientService feignClientService, SettingsService settings) {
        this.feignClientService = feignClientService;
        this.settings = settings;
    }

    public String getGifUrl(boolean isIncreased) {
        return feignClientService.getGiphyApiClient()
                .getGif(settings.getGiphyApiKey(), isIncreased ? RICH_TAG : BROKE_TAG, RATING).getData().getEmbedUrl();

    }
}
