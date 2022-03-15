/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.BookCover;
import entity.Cover;
import entity.History;
import entity.Reader;
import entity.User;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.BookCoverFacade;
import session.BookFacade;
import session.HistoryFacade;
import session.ReaderFacade;
import session.UserRolesFacade;

/**
 *
 * @author user
 */
@WebServlet(name = "ReaderServlet", urlPatterns = {
    "/showTakeOnBooks",
    "/takeOnBook",
    "/showReturnBook",
    "/returnBook",
    
    
})
public class ReaderServlet extends HttpServlet {
    @EJB private ReaderFacade readerFacade;
    @EJB private BookFacade bookFacade;
    @EJB private HistoryFacade historyFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private BookCoverFacade bookCoverFacade;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "Авторизуйтесь!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        if(!userRolesFacade.isRole("READER",authUser)){
            request.setAttribute("info", "У вас нет прав!");
            request.getRequestDispatcher("/showLogin").forward(request, response);
        }
        String path = request.getServletPath();
        switch (path) {
            case "/showTakeOnBooks":
                request.setAttribute("activeShowTakeOnBooks", true);
                List<Book> books = bookFacade.findAll();
                Map<Book,Cover> mapBooks = new HashMap<>();
                for(Book b : books){
                    BookCover bookCover = bookCoverFacade.findCoverByBook(b);
                    mapBooks.put(b, bookCover.getCover());
                }
                request.setAttribute("mapBooks", mapBooks);
                request.getRequestDispatcher("/WEB-INF/showTakeOnBooks.jsp").forward(request, response);
                break;
            case "/takeOnBook":
                String bookId = request.getParameter("bookId");
                Book selectedBook = bookFacade.find(Long.parseLong(bookId));
                selectedBook.setCount(selectedBook.getCount()-1);
                bookFacade.edit(selectedBook);
                History history = new History();
                history.setBook(selectedBook);
                Reader reader = readerFacade.find(authUser.getReader().getId());
                history.setReader(reader);
                history.setGivenBook(Calendar.getInstance().getTime());
                historyFacade.create(history);
                request.setAttribute("info", "Книга выдана");
                request.getRequestDispatcher("/showTakeOnBooks").forward(request, response);
                break;
            case "/showReturnBook":
                request.setAttribute("activeShowReturnBook", true);
                List<History> historyWhisReadingBooks = historyFacade.findHistoriesWithReadingBook(authUser.getReader());
                request.setAttribute("historyWhisReadingBooks", historyWhisReadingBooks);
                request.getRequestDispatcher("/WEB-INF/showReturnBook.jsp").forward(request, response);
                break;
            case "/returnBook":
                String historyId = request.getParameter("historyId");
                History selectedHistory = historyFacade.find(Long.parseLong(historyId));
                Book book = selectedHistory.getBook();
                book.setCount(book.getCount()+1);
                bookFacade.edit(book);
                selectedHistory.setReturnBook(Calendar.getInstance().getTime());
                historyFacade.edit(selectedHistory);
                request.setAttribute("info", "Книга возвращена");
                request.getRequestDispatcher("/showTakeOnBooks").forward(request, response);
                break;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
