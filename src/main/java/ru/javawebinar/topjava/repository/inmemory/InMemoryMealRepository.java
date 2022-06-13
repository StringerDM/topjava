package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final Map<Integer, List<Integer>> userMealValidationMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    @Override
    public Meal save(Meal meal, int userID) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            userMealValidationMap.merge(SecurityUtil.authUserId(),
                    new ArrayList<>(Collections.singletonList(meal.getId())), (list, emptyList) -> {
                        list.add(meal.getId());
                        return list;
                    });
            return meal;
        }
        // handle case: update, but not present in storage
        return validateUser(meal.getId(), userID) ? repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal) : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (validateUser(id, userId)) {
            userMealValidationMap.get(SecurityUtil.authUserId()).remove((Integer) id);
            return repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        return validateUser(id, userId) ? repository.get(id) : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return userMealValidationMap.get(SecurityUtil.authUserId()).stream()
                .map(repository::get)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    public boolean validateUser(int id, int userid) {
        return userMealValidationMap.get(userid).contains(id);
    }
}

