package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.CaloriesPerDayHolder;

import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private boolean excess = false;
    private CaloriesPerDayHolder mealsCalories;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, CaloriesPerDayHolder mealsCalories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.mealsCalories = mealsCalories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setExcess(boolean excess) {
        this.excess = excess;
    }

    private boolean getExcess() {
        if (mealsCalories == null) {
            return excess;
        }
        return mealsCalories.isCaloriesExcess(dateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + getExcess() +
                '}';
    }
}
