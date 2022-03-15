<%-- 
    Document   : addAuthor
    Created on : Feb 4, 2022, 9:29:28 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление автора</title>
    </head>
    <body>
        <h1>Добавить автора</h1>
        <p>${info}</p>
        <form action="createAuthor" method="POST">
            Имя: <input type="text" name="firstName" value="${firstName}"><br>
            Фамилия: <input type="text" name="lastName" value="${lastName}"><br>
            Год рождения: <input type="text" name="birthYear" value="${birthYear}"><br>
            <input type="submit" value="Добавить автора в базу">
        </form>
    </body>
</html>
