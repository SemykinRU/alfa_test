package ru.semykin.alfa_test.service;

import org.springframework.stereotype.Service;

import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@Service
public class GiphyService {

    private final FeignClientService feignClientService;

    public GiphyService(final FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public String getGifUrl(final boolean isIncreased) {
        final String url = feignClientService.getGiphyDto(isIncreased ? RICH_TAG : BROKE_TAG)
                .getData()
                .getEmbedUrl();
        if (url.isBlank()) {
            throw new IllegalArgumentException(FAILED_GET_URL_GIF);
        }

        return String.format(GIF_URL, url);
    }
}
