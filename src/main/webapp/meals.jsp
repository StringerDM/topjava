<%--
  Created by IntelliJ IDEA.
  User: d.medvedev
  Date: 03.06.2022
  Time: 8:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h1><a href="meals?action=add">Add Meal</a></h1>
</body>
<body>
<style>
    table {
        border-collapse: collapse;
    }
    td, th {
        border: 2px solid #333;
    }
</style>
<table>
    <caption></caption>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <%--@elvariable id="mealsTo" type="java.util.List"--%>
    <c:forEach items="${mealsTo}" var="mealTo">
        <tr style="color:${mealTo.excess ? 'green' : 'red'}">
            <td>${mealTo.date} ${mealTo.time}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td>
                <a href="meals?action=edit&id=<c:out value="${mealTo.id}"/>">Update</a>
            </td>
            <td>
                <a href="meals?action=delete&id=<c:out value="${mealTo.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
