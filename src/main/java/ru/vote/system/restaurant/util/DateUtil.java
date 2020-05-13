package ru.vote.system.restaurant.util;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateUtil {
    public static LocalTime STOP_TIME = LocalTime.parse("11:00:00");

    public static boolean isLate(LocalDateTime dateTime) {
        return dateTime.toLocalTime().isAfter(DateUtil.STOP_TIME);
    }
}
