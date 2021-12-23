package main.java.ru.semykin.service;

import org.springframework.stereotype.Service;
import static main.java.ru.semykin.util.ApplicationConstants.*;

@Service
public class GiphyApiService {

    private final FeignClientService feignClientService;

    private final SettingsService settings;

    public GiphyApiService(FeignClientService feignClientService, SettingsService settings) {
        this.feignClientService = feignClientService;
        this.settings = settings;
    }

    public String getGifUrl(boolean isIncreased) {
        String url = feignClientService.getGiphyApiClient()
                .getGif(settings.getGiphyApiKey(), isIncreased ? RICH_TAG : BROKE_TAG, RATING)
                .getData()
                .getEmbedUrl();
        return String.format("<iframe src='%s'></iframe>", url);
    }
}
