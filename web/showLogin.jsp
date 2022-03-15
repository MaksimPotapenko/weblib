<%-- 
    Document   : addAuthor
    Created on : Feb 11, 2022, 9:13:46 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Авторизация</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0  p-5 m-4" style="width: 30rem;">
        <form action="login" method="POST">
            <div class="mb-3">
                <label for="login" class="form-label">Логин</label>
                <input type="text" class="form-control"  name="login" id="login" placeholder="">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Пароль</label>
                <input type="text" class="form-control"  name="password" id="password" placeholder="">
            </div>
            <input class="btn btn-primary my-3" type="submit" value="Войти">
            <h4 class="w-100 mt-3 text-center"><a href="showRegistration">Зарегистрироваться</a></h4>
        </form>
    </div>
</div>

