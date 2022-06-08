package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface Storage {

    void create(Meal meal);

    Meal get(Integer id);

    void update(Meal meal);

    void delete(Integer id);

    List<Meal> getAll();
}
