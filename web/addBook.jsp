<%-- 
    Document   : addAuthor
    Created on : Feb 4, 2022, 9:29:28 AM
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление книги</title>
    </head>
    <body>
        <h1>Добавить новую книгу</h1>
        <p>${info}</p>
        <form action="createBook" method="POST">
            Название книги <input type="text" name="bookName" value="${bookName}"><br>
            Авторы
            <select name="listAuthors" multiple="true">
                <c:forEach var="author" items="${authors}">
                    <option value="${author.id}">${author.firstName} ${author.lastName}</option>
                </c:forEach>
            </select>
            Год издания <input type="text" name="releaseYear" value="${releaseYear}"><br>
            Обложка
            <select name="coverId">
                <c:forEach var="cover" items="${covers}">
                    <option value="${cover.id}">${cover.description}</option>
                </c:forEach>
            </select>
            Количество экземпляров <input type="text" name="quantity" value="${quantity}"><br>
            <input type="submit" value="Добавить новую книгу">
        </form>
    </body>
</html>
