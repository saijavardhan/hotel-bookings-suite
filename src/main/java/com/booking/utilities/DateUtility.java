package com.booking.utilities;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtility {

    /**
     * +
     *
     * @type: Method
     * @Usage: This will return the date + or - specific dates from current date
     * @parameters: String, String
     * @return: String
     */
    public static String convertToDate(String format, String date) {
        String dateInString =new SimpleDateFormat(format).format(new Date());
        LocalDate localDate = LocalDate.parse(dateInString);
        if (date.contains("+")) {
            Long days = Long.valueOf(date.split("\\+")[1]);
            return localDate.plusDays(1).toString();
        }
        if (date.contains("-")) {
            Long days = Long.valueOf(date.split("-")[1]);
            return localDate.minusDays(days).toString();
        }
        return localDate.toString();

    }
}
