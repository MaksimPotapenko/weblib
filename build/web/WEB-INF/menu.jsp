<%-- 
    Document   : menu
    Created on : Feb 14, 2022, 12:38:53 PM
    Author     : user
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    
      <a class="navbar-brand" href="listBooks">SPTV20WebLibrary</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
          <c:choose>
              <c:when test="${topRole eq 'ADMINISTRATOR'}">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"> <a class='nav-link <c:if test="${activeAddAuthor eq true}">active</c:if>' aria-current="page" href="addAuthor">Добавить автора</a> </li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeAddBook eq true}">active</c:if>'  aria-current="page" href="addBook">Добавить книгу</a></li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowTakeOnBooks eq true}">active</c:if>'  aria-current="page" href="showTakeOnBooks">Взять книгу</a></li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowReturnBook eq true}">active</c:if>'  aria-current="page" href="showReturnBook">Вернуть книгу</a> </li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowAdminPanel eq true}">active</c:if>'  aria-current="page" href="showAdminPanel">Панель администратора</a> </li>
                </ul>
              </c:when>
              <c:when test="${topRole eq 'MANAGER'}">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"> <a class='nav-link <c:if test="${activeAddAuthor eq true}">active</c:if>' aria-current="page" href="addAuthor">Добавить автора</a> </li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeAddBook eq true}">active</c:if>'  aria-current="page" href="addBook">Добавить книгу</a></li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowTakeOnBooks eq true}">active</c:if>'  aria-current="page" href="showTakeOnBooks">Взять книгу</a></li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowReturnBook eq true}">active</c:if>'  aria-current="page" href="showReturnBook">Вернуть книгу</a> </li>
                </ul>
              </c:when>
              <c:when test="${topRole eq 'READER'}">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowTakeOnBooks eq true}">active</c:if>'  aria-current="page" href="showTakeOnBooks">Взять книгу</a></li>
                    <li class="nav-item"><a class='nav-link <c:if test="${activeShowReturnBook eq true}">active</c:if>'  aria-current="page" href="showReturnBook">Вернуть книгу</a> </li>
                </ul>
              </c:when>
              <c:when test="${topRole eq NULL}">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item"> <a class='nav-link <c:if test="${activeListBooks eq true}">active</c:if>' aria-current="page" href="listBooks">Список книг</a> </li>
                </ul>
              </c:when>
          </c:choose>
      <ul class="navbar-nav mb-2 mb-lg-0">
          <c:if test="${authUser eq null}">
            <li class="nav-item">
              <a class='nav-link <c:if test="${activeShowLogin eq true}">active</c:if>'  aria-current="page" href="showLogin">Вход</a>
            </li>
          </c:if>
          <c:if test="${authUser ne null}">
            <li class="nav-item">
              <a class='nav-link <c:if test="${activeLogout eq true}">active</c:if>'  aria-current="page" href="logout">Выход</a>
            </li>
          </c:if>
        
        </ul>
        <form class="d-flex">
          <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
          <button class="btn btn-outline-success" type="submit">Search</button>
        </form>
      
    
</nav>
