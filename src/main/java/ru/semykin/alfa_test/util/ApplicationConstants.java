package ru.semykin.alfa_test.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface ApplicationConstants {

    String TODAY = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

   int ONE_DAY = 1;

   String YESTERDAY = LocalDate.now()
            .minusDays(ONE_DAY)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    String RICH_TAG = "#rich";

    String BROKE_TAG = "#broke";

    String RATING = "pg-13";

    String TEST_UE = "RUB";

    String FAILED_GET_CURRENCY_DATA = "Не пришли данные по валюте %s за %s";

    String FAILED_GET_GIF = "Не удалось получить gif по тагу %s";

    String FAILED_GET_URL_GIF = "Не удалось получить ссылку на gif";

    String GIF_URL = "<iframe src='%s'></iframe>";

}