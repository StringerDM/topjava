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
    <c:forEach items="${mealsTo}" var="mealTo">
        <tr style="color:${mealTo.excess ? 'green' : 'red'}">
            <td>${mealTo.date} ${mealTo.time}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td>
                <a href="meals?action=update&id=${mealTo.id}">Update</a>
            </td>
            <td>
                <a href="meals?action=delete&id=${mealTo.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
