package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.TestMeal;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    public static final int CALORIES = 2000;
    private static final String LIST_MEALS = "meals.jsp";
    private static final Logger log = getLogger(MealServlet.class);
    private final Storage storage = new MealStorage();

    {
        TestMeal.createMeals().forEach(storage::save);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        request.setAttribute("mealsTo", MealsUtil.filteredByStreams(storage.getAll(), LocalTime.of(0, 0),
                LocalTime.of(23, 59), CALORIES));
        String forward = LIST_MEALS;
        request.getRequestDispatcher("meals.jsp").forward(request, response);

        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            storage.delete(id);
            forward = LIST_MEALS;
        } else if (action.equalsIgnoreCase("edit")) {
            forward = "addMeal.jsp";
            Integer id = Integer.parseInt(request.getParameter("id"));
            Meal meal = storage.get(id);
            request.setAttribute("meal", meal);
            storage.update(meal);
        } else if (action.equalsIgnoreCase("add")) {
            forward = "addMeal.jsp";
        }
        response.sendRedirect(forward);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime localDateTime = java.time.LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        request.getRequestDispatcher("meals.jsp").forward(request, response);
        Meal meal = new Meal(localDateTime, description, calories);
        storage.save(meal);
        request.setAttribute("mealsTo", MealsUtil.filteredByStreams(storage.getAll(), LocalTime.of(0, 0),
                LocalTime.of(23, 59), 2000));
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);

    }
}
