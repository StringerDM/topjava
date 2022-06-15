package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        MealsUtil.meals.forEach(meal -> {
            Meal newMeal = new Meal(meal.getDateTime(), meal.getDescription() + " userId = 2", meal.getCalories());
            save(newMeal, 2);
        });
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            if (!repository.containsKey(userId)) {
                repository.put(userId, new ConcurrentHashMap<>());
            }
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        return repository.containsKey(userId) ? repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal) : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return repository.containsKey(userId) && repository.get(userId).remove(id) != null;

    }

    @Override
    public Meal get(int id, int userId) {
        return repository.containsKey(userId) ? repository.get(userId).get(id) : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repository.containsKey(userId) ? repository.get(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList()) : new ArrayList<>();
    }

    @Override
    public List<Meal> getAllFilteredByDate(int userId, LocalDateTime start, LocalDateTime end) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime(), start, end))
                .collect(Collectors.toList());
    }
}

