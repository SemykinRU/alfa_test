package main.java.ru.semykin.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public interface ApplicationConstants {

    String TODAY = LocalDate.now()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

   int ONE_DAY = 1;

   String YESTERDAY = LocalDate.now()
            .minusDays(ONE_DAY)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    String RICH_TAG = "rich";

    String BROKE_TAG = "broke";

    String RATING = "r";

}
