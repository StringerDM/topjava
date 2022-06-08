<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<section>
    <form method="POST" action="meals?action=update">
        DateTime : <label>
        <input type="datetime-local" name="dateTime" value="${meal.dateTime}"/>
    </label> <br/>
        Description : <label>
        <input type="text" name="description" value="<c:out value="${meal.description}"/>"/>
    </label> <br/>
        Calories : <label>
        <input type="text" name="calories" value="<c:out value="${meal.calories}"/>"/>
    </label> <br/>
        <input type="hidden" name="id" value="<c:out value="${meal.id}"/>">
        <button type="submit">Save</button>
    </form>
</section>
</body>
</html>
