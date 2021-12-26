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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static ru.semykin.alfa_test.util.ApplicationConstants.*;

@SpringBootTest
class GiphyServiceTest {

    @MockBean
    private GiphyClient giphyClient;

    @Autowired
    private SettingsService settings;

    @Autowired
    private GiphyService giphyService;

    private GiphyDto richGiphy = new GiphyDto();
    private GiphyDto brokeGiphy = new GiphyDto();
    private final GiphyDto blankGiphy = new GiphyDto();
    private GiphyDataDto richData = new GiphyDataDto();
    private GiphyDataDto brokeData = new GiphyDataDto();
    private final GiphyDataDto blank = new GiphyDataDto();

    @BeforeEach
    public void startUP() {
        richData.setEmbedUrl("rich_url");
        brokeData.setEmbedUrl("broke_url");
        blank.setEmbedUrl("");
        richGiphy.setData(richData);
        brokeGiphy.setData(brokeData);
        blankGiphy.setData(blank);
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

    @Test
    void whenReturnIsBlankUrlThenIllegalArgumentException() {
        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        BROKE_TAG,
                        RATING))
                .thenReturn(blankGiphy);
        assertThrows(IllegalArgumentException.class, () -> giphyService.getGifUrl(false));
    }

    @Test
    void whenReturnNullableGiphyDataDtoThenIllegalArgumentException() {
        when(giphyClient
                .getGif(settings.getGiphyApiKey(),
                        BROKE_TAG,
                        RATING))
                .thenReturn(null);
        assertThrows(IllegalArgumentException.class, () -> giphyService.getGifUrl(false));
    }
}