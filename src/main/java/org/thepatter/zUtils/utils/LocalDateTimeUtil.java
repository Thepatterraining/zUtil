package org.thepatter.zUtils.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {

    public static String TIME_FORMATTER_NORMAL = "yyyy-MM-dd HH:mm:ss";

    public static Integer ZONE_OFFSET_HOURS = 8;

    public static String format(LocalDateTime time) {
        return LocalDateTimeUtil.format(time,TIME_FORMATTER_NORMAL);
    }

    public static String format(LocalDateTime time, String formatter) {
        return time.format(DateTimeFormatter.ofPattern(formatter));
    }

    public static String format(LocalDateTime time, DateTimeFormatter formatter) {
        return time.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public static long toTimestampAtSeconds(LocalDateTime time) {
        return time.toEpochSecond(ZoneOffset.ofHours(ZONE_OFFSET_HOURS));
    }

    public static long toTimestampAtMilliseconds(LocalDateTime time) {
        return time.toInstant(ZoneOffset.ofHours(ZONE_OFFSET_HOURS)).toEpochMilli();
    }

    public static LocalDateTime toLocalDateTimeByMillis(long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneOffset.ofHours(ZONE_OFFSET_HOURS));
    }

    public static LocalDateTime toLocalDateTimeBySeconds(long seconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), ZoneOffset.ofHours(ZONE_OFFSET_HOURS));
    }
}
