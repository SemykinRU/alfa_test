package ru.semykin.alfa_test.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.semykin.alfa_test.client.GiphyClient;
import ru.semykin.alfa_test.dto.GiphyDataDto;
import ru.semykin.alfa_test.dto.GiphyDto;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@SpringBootTest
class GiphyServiceTest {

    @Autowired
    FeignClientService feignClientService;

    @MockBean
    GiphyClient giphyClient;

    @Autowired
    SettingsService settings;

    @Autowired
    GiphyService giphyService;

    GiphyDto richGiphy = new GiphyDto();
    GiphyDto brokeGiphy = new GiphyDto();
    GiphyDataDto richData = new GiphyDataDto();
    GiphyDataDto brokeData = new GiphyDataDto();

    @BeforeEach
    public void startUP() {
        richData.setEmbedUrl("rich_url");
        brokeData.setEmbedUrl("broke_url");
        richGiphy.setData(richData);
        brokeGiphy.setData(brokeData);
    }

    @AfterEach
    public void tierDown() {
        richData = brokeData = null;
        richGiphy = brokeGiphy = null;
    }

    @Test
    void whenGetGiphyByRichTagThen() {
        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        RICH_TAG,
                        RATING))
                .thenReturn(richGiphy);
        assertTrue(giphyService.getGifUrl(true).contains("rich_url"));
    }

    @Test
    void whenGetGiphyByBrokeTagThen() {
        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        BROKE_TAG,
                        RATING))
                .thenReturn(brokeGiphy);
        assertTrue(giphyService.getGifUrl(false).contains("broke_url"));
    }
}