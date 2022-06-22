package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {
    public static final LocalDate START_DATE = LocalDate.of(2020, Month.JANUARY, 30);
    public static final LocalDate END_DATE = LocalDate.of(2020, Month.JANUARY, 30);

    public static final Meal USER_MEAL_1 = new Meal(100003, LocalDateTime
            .of(2020, Month.JANUARY, 30, 10, 0), "Завтрак - user", 500);
    public static final Meal USER_MEAL_2 = new Meal(100004, LocalDateTime
            .of(2020, Month.JANUARY, 30, 13, 0), "Обед - user", 1000);
    public static final Meal USER_MEAL_3 = new Meal(100005, LocalDateTime
            .of(2020, Month.JANUARY, 30, 20, 0), "Ужин - user", 500);
    public static final Meal USER_MEAL_4 = new Meal(100006, LocalDateTime
            .of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение - user", 100);
    public static final Meal USER_MEAL_5 = new Meal(100007, LocalDateTime
            .of(2020, Month.JANUARY, 31, 10, 0), "Завтрак - user", 1000);
    public static final Meal USER_MEAL_6 = new Meal(100008, LocalDateTime
            .of(2020, Month.JANUARY, 31, 13, 0), "Обед - user", 500);
    public static final Meal USER_MEAL_7 = new Meal(100009, LocalDateTime
            .of(2020, Month.JANUARY, 31, 20, 0), "Ужин - user", 410);

    public static final Meal ADMIN_MEAL_1 = new Meal(100010, LocalDateTime
            .of(2020, Month.JANUARY, 30, 10, 0), "Завтрак - admin", 500);
    public static final Meal ADMIN_MEAL_2 = new Meal(100011, LocalDateTime
            .of(2020, Month.JANUARY, 30, 13, 0), "Обед - admin", 1000);
    public static final Meal ADMIN_MEAL_3 = new Meal(100012, LocalDateTime
            .of(2020, Month.JANUARY, 30, 20, 0), "Ужин - admin", 500);
    public static final Meal ADMIN_MEAL_4 = new Meal(100013, LocalDateTime
            .of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение - admin", 100);
    public static final Meal ADMIN_MEAL_5 = new Meal(100014, LocalDateTime
            .of(2020, Month.JANUARY, 31, 10, 0), "Завтрак - admin", 1000);
    public static final Meal ADMIN_MEAL_6 = new Meal(100015, LocalDateTime
            .of(2020, Month.JANUARY, 31, 13, 0), "Обед - admin", 500);
    public static final Meal ADMIN_MEAL_7 = new Meal(100016, LocalDateTime
            .of(2020, Month.JANUARY, 31, 20, 0), "Ужин - admin", 410);

    public static Meal getNewMeal() {
        return new Meal(LocalDateTime.of(2022, Month.JUNE, 22, 15, 47), "новая еда", 333);
    }

    public static Meal getUpdated(Meal meal) {
        Meal updated = new Meal(meal);
        updated.setDateTime(LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0));
        updated.setDescription("UpdatedDescription");
        updated.setCalories(550);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
