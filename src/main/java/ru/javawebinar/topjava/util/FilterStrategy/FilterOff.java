package ru.javawebinar.topjava.util.FilterStrategy;

import java.time.LocalTime;

public class FilterOff implements FilterStrategy {

    @Override
    public boolean filter(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return true;
    }
}