package main.java.ru.semykin.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class CurrencyModel {
    private Map<String, Double> rates;
}
