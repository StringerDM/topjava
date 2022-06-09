<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meal</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>${param.action == 'create' ? 'Create meal' : 'Edit meal'}</h2>
    <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
    <form method="POST" action="meals">
        <input type="hidden" name="id" value="${meal.id}">
        DateTime : <label>
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/>
    </label> <br/>
        Description : <label>
        <input type="text" name="description" value="${meal.description}"/>
    </label> <br/>
        Calories : <label>
        <input type="number" name="calories" value="${meal.calories}"/>
    </label> <br/>
        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
