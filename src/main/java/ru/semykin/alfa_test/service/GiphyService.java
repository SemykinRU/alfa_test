package ru.semykin.alfa_test.service;

import org.springframework.stereotype.Service;
import ru.semykin.alfa_test.dto.GiphyDataDto;

import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@Service
public class GiphyService {

    private final FeignClientService feignClientService;

    public GiphyService(final FeignClientService feignClientService) {
        this.feignClientService = feignClientService;
    }

    public String getGifUrl(final boolean isIncreased) {
        final String url = getGiphyDataDto(isIncreased).getEmbedUrl();
        return String.format(GIF_URL, url);
    }

    private GiphyDataDto getGiphyDataDto(final boolean isIncreased) {
        final GiphyDataDto dataDto = feignClientService.getGiphyDto(isIncreased ? RICH_TAG : BROKE_TAG).getData();
        if (dataDto == null || dataDto.getEmbedUrl().isBlank()) {
            throw new IllegalArgumentException(FAILED_GET_URL_GIF);
        }
        return dataDto;
    }
}
