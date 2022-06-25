package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.NOT_FOUND;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(adminMeal1.getId(), ADMIN_ID);
        assertMatch(meal, adminMeal1);
    }

    @Test
    public void getForeign() {
        assertThrows(NotFoundException.class, () ->
                mealService.get(userMeal1.getId(), ADMIN_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.get(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void delete() {
        mealService.delete(adminMeal1.getId(), ADMIN_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(adminMeal1.getId(), ADMIN_ID));
    }

    @Test
    public void deleteForeign() {
        assertThrows(Exception.class, () ->
                mealService.delete(userMeal1.getId(), ADMIN_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> mealService.delete(NOT_FOUND, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> list = mealService.getBetweenInclusive(START_DATE, END_DATE, ADMIN_ID);
        assertMatch(list, adminMeal3, adminMeal2, adminMeal1);
    }

    @Test
    public void getAll() {
        List<Meal> all = mealService.getAll(ADMIN_ID);
        assertMatch(all, adminMeal7, adminMeal6, adminMeal5, adminMeal4, adminMeal3, adminMeal2, adminMeal1);
    }

    @Test
    public void update() {
        Meal updated = getUpdated(adminMeal1);
        mealService.update(updated, ADMIN_ID);
        assertMatch(mealService.get(adminMeal1.getId(), ADMIN_ID), updated);
    }

    @Test
    public void updateForeign() {
        assertThrows(NotFoundException.class, () -> mealService.update(userMeal1, ADMIN_ID));
    }

    @Test
    public void updateNotFound() {
        Meal meal = getNewMeal();
        meal.setId(NOT_FOUND);
        assertThrows(NotFoundException.class, () -> mealService.update(meal, ADMIN_ID));
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNewMeal(), ADMIN_ID);
        Integer newId = created.getId();
        Meal newMeal = getNewMeal();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(mealService.get(newId, ADMIN_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        Meal meal = getNewMeal();
        meal.setDateTime(adminMeal1.getDateTime());
        assertThrows(DataAccessException.class, () -> mealService.create(meal, ADMIN_ID));
    }
}