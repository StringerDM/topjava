package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealMapStorage;
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
    private final Storage storage = new MealMapStorage();

    {
        MealsUtil.createMeals().forEach(storage::create);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward = "";
        String action = request.getParameter("action");
        switch (action == null ? "listMeals" : action) {
            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                storage.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
                response.sendRedirect("addMeals.jsp");
                break;
            case "update":
                id = Integer.parseInt(request.getParameter("id"));
                Meal meal = storage.get(id);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("addMeal.jsp").forward(request, response);
                break;
            case "listMeals":
                request.setAttribute("mealsTo", MealsUtil.filteredByStreams(storage.getAll(), LocalTime.MIN,
                        LocalTime.MAX, CALORIES));
                request.getRequestDispatcher("meals.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LocalDateTime localDateTime = java.time.LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        switch (request.getParameter("action")) {
            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                Meal meal = new Meal(localDateTime, description, calories);
                meal.setId(id);
                storage.update(meal);
                break;
            case "create":
                storage.create(new Meal(localDateTime, description, calories));
        }
        response.sendRedirect("meals");






//        request.setAttribute("mealsTo", MealsUtil.filteredByStreams(storage.getAll(), LocalTime.of(0, 0),
//                LocalTime.of(23, 59), 2000));
//        request.getRequestDispatcher("meals.jsp").forward(request, response);
    }
}
