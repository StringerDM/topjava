package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class UserMealWithExcessCollector implements Collector<UserMeal, List<UserMeal>, List<UserMealWithExcess>> {
    private final Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
    private final List<UserMealWithExcess> userMealWithExcessList = new ArrayList<>();
    private final int caloriesPerDay;
    private final LocalTime startTime;
    private final LocalTime endTime;

    private UserMealWithExcessCollector(LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static UserMealWithExcessCollector toUserMealWithExcess(LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        return new UserMealWithExcessCollector(startTime, endTime, caloriesPerDay);
    }

    @Override
    public Supplier<List<UserMeal>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<UserMeal>, UserMeal> accumulator() {
        return (userMeals, userMeal) -> {
            caloriesPerDayMap.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
            userMeals.add(userMeal);
        };
    }

    @Override
    public BinaryOperator<List<UserMeal>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<UserMeal>, List<UserMealWithExcess>> finisher() {
        return userMeals -> {
            userMeals.forEach(userMeal -> {
                if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                    userMealWithExcessList.add(new UserMealWithExcess(
                            userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
                            caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
                }
            });
            return userMealWithExcessList;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
