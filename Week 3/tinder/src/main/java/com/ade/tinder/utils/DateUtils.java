package com.ade.tinder.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
        return format.format(date);
    }

}
