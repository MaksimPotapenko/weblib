<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Список читаемых книг</h1>
<div class="w-100 d-flex justify-content-center">
    <c:forEach var="entry" items="${mapHistoryWidthReadengBook}">
        <div class="card m-2" style="width: 10rem;">
          <img src="insertFile/${entry.value.fileName}" class="card-img-top" >
          <div class="card-body">
            <h5 class="card-title">${entry.key.book.bookName}</h5>
            <p class="card-text">
                <c:forEach var="author" items="${entry.key.book.authors}">
                    ${author.firstName} ${author.lastName}.
                </c:forEach>
            </p>
            <p class="card-text">${entry.key.book.releaseYear}</p>
            
            <p class="card-text"><a href="returnBook?historyId=${entry.key.id}">Вернуть книгу</a></p>
          </div>
        </div>
    </c:forEach>
</div>
       
