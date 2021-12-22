package main.java.ru.semykin.service;

import com.google.gson.Gson;
import main.java.ru.semykin.model.CurrencyModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CurrencyApiService {
    private final CurrencyApiClient currencyApiClient;

    private final String key = "RUB";
    private final String today = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private final int minusDay = 1;
    private final String yesterday = LocalDate.now()
            .minusDays(minusDay)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private final String appID = "64c2e5b33da64ef9968d3fc497bee65b";

    public CurrencyApiService(CurrencyApiClient currencyApiClient) {
        this.currencyApiClient = currencyApiClient;
    }

    public boolean isIncreased() {
        Gson gson = new Gson();
        CurrencyModel todayCurrency =
                gson.fromJson(currencyApiClient.readToDayCurrency(today, appID, key), CurrencyModel.class);
        CurrencyModel yesterdayCurrency =
                gson.fromJson(currencyApiClient.readToDayCurrency(yesterday, appID, key), CurrencyModel.class);
        return todayCurrency.getRates().get(key) > yesterdayCurrency.getRates().get(key);
    }
}
