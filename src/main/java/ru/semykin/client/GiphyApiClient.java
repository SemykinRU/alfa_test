package main.java.ru.semykin.client;

import main.java.ru.semykin.dto.GiphyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy-api", url = "${giphy.url}")
public interface GiphyApiClient {
    @GetMapping(value = "/random")
    GiphyDto  getGif(@RequestParam("api_key") String apiKey,
                    @RequestParam("tag") String tag,
                    @RequestParam("rating") String rating);
}
