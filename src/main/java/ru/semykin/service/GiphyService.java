package main.java.ru.semykin.service;

import org.springframework.stereotype.Service;
import static main.java.ru.semykin.util.ApplicationConstants.*;

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
