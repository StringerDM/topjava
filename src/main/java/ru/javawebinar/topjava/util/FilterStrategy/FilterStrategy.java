package ru.javawebinar.topjava.util.FilterStrategy;

import java.time.LocalTime;

public interface FilterStrategy {

    boolean filter(LocalTime lt, LocalTime startTime, LocalTime endTime);
}