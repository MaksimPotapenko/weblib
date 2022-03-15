<%-- 
    Document   : addAuthor
    Created on : Feb 11, 2022, 9:13:46 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Добавить автора</h1>
<div class="w-100 d-flex justify-content-center">
    <div class="card border-0  p-5 m-4" style="width: 30rem;">
        <form action="createAuthor" method="POST">
            <div class="mb-3">
                <label for="firstName" class="form-label">Имя</label>
                <input type="text" class="form-control"  name="firstName" id="firstName" placeholder="">
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Фамилия</label>
                <input type="text" class="form-control"  name="lastName" id="lastName" placeholder="">
            </div>
            <div class="mb-3">
                <label for="birthYear" class="form-label">Год рождения</label>
                <input type="text" class="form-control w-50"  name="birthYear" id="birthYear" placeholder="">
            </div>
            <input class="btn btn-primary my-3" type="submit" value="Запомнить">
        </form>
    </div>
</div>

