<%--
  Created by IntelliJ IDEA.
  User: d.medvedev
  Date: 05.06.2022
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<%--@elvariable id="meal" type="java.model.Meal"--%>
<form method="POST" action="meal" name="frmAddMeal">
    DateTime : <input type="datetime-local" name="dateTime" value="<c:out value="${meal.dateTime}"/>"/> <br/>
    Description : <input type="text" name="description" value="<c:out value="${meal.description}"/>"/> <br/>
    Calories : <input type="text" name="calories" value="<c:out value="${meal.calories}"/>"/> <br/>
    <input type="submit" value="Submit"/>

</form>
</body>
</html>
