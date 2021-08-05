package com.library.manage.util;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final String DATEFORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DATEFORMAT_PATTERN_TO_DAY = "yyyy-MM-dd";

    private static final String DATEFORMAT_PATTERN_TO_MIN = "yyyy-MM-dd HH:mm";

    private static final String DATEFORMAT_PATTERN_TO_MIN_CN = "yyyy年MM月dd日 HH时mm分";

    private static final String DATEFORMAT_PATTERN_TO_DAY_CN = "yyyy年MM月dd日";

    private static SimpleDateFormat dateFormat;

    private DateUtil(){}

    @SneakyThrows(ParseException.class)
    public static Date dateFormat(@NonNull Date date){
        dateFormat = new SimpleDateFormat(DATEFORMAT_PATTERN);
        return dateFormat.parse(dateFormat.format(date));
    }

    public static String dateFormat_To_Day(Date date){
        dateFormat = new SimpleDateFormat(DATEFORMAT_PATTERN_TO_DAY);
        return dateFormat.format(date);
    }

    @SneakyThrows(ParseException.class)
    public static Date dateFormat_To_Min(@NonNull Date date){
        dateFormat = new SimpleDateFormat(DATEFORMAT_PATTERN_TO_MIN);
        return dateFormat.parse(dateFormat.format(date));
    }

    @SneakyThrows(ParseException.class)
    public static Date dateFormat_To_Min_CN(@NonNull Date date){
        dateFormat = new SimpleDateFormat(DATEFORMAT_PATTERN_TO_MIN_CN);
        return dateFormat.parse(dateFormat.format(date));
    }

    @SneakyThrows(ParseException.class)
    public static Date dateFormat_To_Day_CN(@NonNull Date date){
        dateFormat = new SimpleDateFormat(DATEFORMAT_PATTERN_TO_DAY_CN);
        return dateFormat.parse(dateFormat.format(date));
    }
}
