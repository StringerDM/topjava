package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CaloriesPerDayHolder {
    private final int caloriesPerDay;
    private final Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();

    public CaloriesPerDayHolder(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public void addCalories(LocalDate date, int mealCalories) {
        caloriesPerDayMap.merge(date, mealCalories, Integer::sum);
    }

    public boolean isCaloriesExcess(LocalDate date) {
        return caloriesPerDayMap.get(date) > caloriesPerDay;
    }


}
