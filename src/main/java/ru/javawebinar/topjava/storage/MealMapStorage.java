package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MealMapStorage implements Storage {
    private final Map<Integer, Meal> storage = new ConcurrentHashMap<>();
    private Integer count = 0;

    @Override
    public synchronized void create(Meal meal) {
        storage.put(count, meal);
        meal.setId(count);
        count++;
    }

    @Override
    public Meal get(Integer id) {
        return storage.get(id);
    }

    @Override
    public void update(Meal meal) {
        if (!storage.containsKey(meal.getId())) {
            storage.put(meal.getId(), meal);
        } else {
            System.out.println("Error: meal id " + meal.getId() + " is not exist");
        }
    }

    @Override
    public void delete(Integer id) {
        storage.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
