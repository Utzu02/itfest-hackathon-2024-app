package com.davtheutz.adobeitfestv3.utils;

import android.os.Build;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReportingUtils {

    public static String getCurrentTimeFormatted() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        return timeFormat.format(currentTime);
    }

}
