package ru.semykin.alfa_test.service;

import org.springframework.stereotype.Service;
import static ru.semykin.alfa_test.util.ApplicationConstants.BROKE_TAG;
import static ru.semykin.alfa_test.util.ApplicationConstants.RICH_TAG;

@Service
public class GiphyService {

    private final FeignClientService feignClientService;

    public GiphyService(FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public String getGifUrl(boolean isIncreased) {
        String url = feignClientService.getGiphyDto(isIncreased ? RICH_TAG : BROKE_TAG)
                .getData()
                .getEmbedUrl();
        return String.format("<iframe src='%s'></iframe>", url);
    }
}
