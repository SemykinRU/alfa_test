package ru.semykin.alfa_test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.semykin.alfa_test.dto.GiphyDto;

@FeignClient(value = "giphy-api", url = "${giphy.url}")
public interface GiphyClient {
    @GetMapping(value = "/random")
    GiphyDto getGif(@RequestParam("api_key") String apiKey,
                    @RequestParam("tag") String tag,
                    @RequestParam("rating") String rating);
}
