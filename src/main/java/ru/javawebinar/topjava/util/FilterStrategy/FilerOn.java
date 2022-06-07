package ru.javawebinar.topjava.util.FilterStrategy;

import java.time.LocalTime;

public class FilerOn implements FilterStrategy{

    @Override
    public boolean filter(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }
}