package ru.semykin.alfa_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GiphyDataDto {

    @JsonProperty("embed_url")
    private String embedUrl;
}
