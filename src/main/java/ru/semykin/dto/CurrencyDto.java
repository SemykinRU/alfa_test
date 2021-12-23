package main.java.ru.semykin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CurrencyDto {

    private long timestamp;

    private String base;

    private Map<String, Double> rates;
}
