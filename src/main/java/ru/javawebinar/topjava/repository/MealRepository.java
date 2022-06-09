package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    Meal get(Integer id);

    void delete(Integer id);

    List<Meal> getAll();
}
