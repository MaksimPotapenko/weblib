<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Список книг</h1>
<div class="w-100 d-flex justify-content-center">
    <c:forEach var="entry" items="${mapBooks}">
        <div class="card m-2" style="width: 15rem;">
            <img src="insertFile/${entry.value.fileName}" class="card-img-top" >
          <div class="card-body">
            <h5 class="card-title">${entry.key.bookName}</h5>
            <p class="card-text">
                <c:forEach var="author" items="${entry.key.authors}">
                    ${author.firstName} ${author.lastName}.
                </c:forEach>
            </p>
            <p class="card-text">${entry.key.releaseYear}</p>
            <p class="card-text">${entry.key.count} шт.</p>
            <p class="card-text"><a href="takeOnBook?bookId=${entry.key.id}">Взять книгу</a></p>
          </div>
        </div>
    </c:forEach>
</div>
       
