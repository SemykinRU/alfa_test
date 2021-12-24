package ru.semykin.alfa_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class GiphyDataDto {

    @JsonProperty("embed_url")
    private String embedUrl;
}
