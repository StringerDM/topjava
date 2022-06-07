package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealStorage implements Storage {
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private final AtomicInteger count = new AtomicInteger(0);

    @Override
    public void save(Meal meal) {
        storage.put(count.incrementAndGet(), meal);
        meal.setId(count.intValue());
    }

    @Override
    public Meal get(Integer id) {
        return storage.get(id);
    }

    @Override
    public void update(Meal meal) {
        storage.put(meal.getId(), meal);
    }

    @Override
    public void delete(Integer id) {
        storage.remove(id);
    }

    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
