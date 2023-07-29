package com.ade.tinder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        return format.format(date);
    }

    public static int compare(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        try {
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);
            return d1.compareTo(d2);
        } catch (ParseException e) {
            return 0;
        }
    }

}
