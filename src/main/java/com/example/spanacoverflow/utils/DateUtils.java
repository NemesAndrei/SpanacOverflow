package com.example.spanacoverflow.utils;

import java.sql.Timestamp;

public class DateUtils {

    public static Timestamp getDate() {
        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(sdf.format(date));

    }
}
