package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return dateTimeComparator(lt, startTime, endTime);
    }

    public static boolean isBetweenHalfOpen(LocalDateTime ld, LocalDateTime startDate, LocalDateTime endDate) {
        dateTimeComparator(ld, startDate, endDate);
        return dateTimeComparator(ld, startDate, endDate);
    }

    private static <T extends Comparable<T>> boolean dateTimeComparator(T t, T start, T end) {
        return t.compareTo(start) >= 0 && t.compareTo(end) < 0;
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

