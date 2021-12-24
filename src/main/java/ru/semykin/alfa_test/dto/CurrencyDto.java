package ru.semykin.alfa_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Map;

@ToString
@Data
public class CurrencyDto {

    @JsonProperty("timestamp")
    private long timestamp;

    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private Map<String, Double> rates;

}
