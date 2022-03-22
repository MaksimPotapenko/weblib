/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Book;
import entity.BookCover;
import entity.Cover;
import entity.Reader;
import entity.Role;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
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
import session.ReaderFacade;
import session.RoleFacade;
import session.UserFacade;
import session.UserRolesFacade;

/**
 *
 * @author jvm
 */
@WebServlet(name = "LoginServlet",loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/listBooks",
    "/showRegistration",
    "/registration",
    
    
})
public class LoginServlet extends HttpServlet {
    @EJB private BookFacade bookFacade;
    @EJB private UserFacade userFacade;
    @EJB private ReaderFacade readerFacade;
    @EJB private RoleFacade roleFacade;
    @EJB private UserRolesFacade userRolesFacade;
    @EJB private BookCoverFacade bookCoverFacade;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        if(userFacade.count() != 0) return;
        Reader reader = new Reader();
        reader.setFirstname("Juri");
        reader.setLastname("Melnikov");
        reader.setPhone("5654456767");
        readerFacade.create(reader);
        User user = new User();
        user.setLogin("admin");
        user.setPassword("12345");
        user.setReader(reader);
        userFacade.create(user);
        Role role = new Role();
        role.setRoleName("READER");
        roleFacade.create(role);
        UserRoles userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
        role = new Role();
        role.setRoleName("MANAGER");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
        role = new Role();
        role.setRoleName("ADMINISTRATOR");
        roleFacade.create(role);
        userRoles = new UserRoles();
        userRoles.setRole(role);
        userRoles.setUser(user);
        userRolesFacade.create(userRoles);
    }

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
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/showLogin":
                request.setAttribute("activeShowLogin", true);
                request.getRequestDispatcher("/showLogin.jsp").forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                // Authentification
                User authUser = userFacade.findByLogin(login);
                if(authUser == null){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                }
                //Authorization
                if(!password.equals(authUser.getPassword())){
                    request.setAttribute("info", "Неверный логин или пароль");
                    request.getRequestDispatcher("/showLogin").forward(request, response);
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", authUser);
                String topRoleAuthUser = userRolesFacade.getTopRole(authUser);
                session.setAttribute("topRole", topRoleAuthUser);
//                request.setAttribute("topRoleAuthUser", topRoleAuthUser);
                request.setAttribute("info", "Здравствуйте "+authUser.getReader().getFirstname());
                request.getRequestDispatcher("/listBooks").forward(request, response);
                break;
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
                request.setAttribute("activeLogout", true);
                request.getRequestDispatcher("/listBooks").forward(request, response);
                break;
            case "/listBooks":
                List<Book> books = bookFacade.findAll();
                Map<Book,Cover> mapBooks = new HashMap<>();
                for(Book b : books){
                    BookCover bookCover = bookCoverFacade.findCoverByBook(b);
                    mapBooks.put(b, bookCover.getCover());
                }
                request.setAttribute("mapBooks", mapBooks);
                request.setAttribute("activeListBooks", true);
                request.getRequestDispatcher("/listBooks.jsp").forward(request, response);
                break;    
            case "/showRegistration":
                request.getRequestDispatcher("/showRegistration.jsp").forward(request, response);
                break;
            case "/registration":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if(!password1.equals(password2)){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Не совпадают пароли");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                if("".equals(firstname) 
                        || "".equals(lastname)
                        || "".equals(phone)
                        || "".equals(login)
                        || "".equals(password1)
                        || "".equals(password2)
                        ){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Заполните все поля");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                Reader reader = new Reader();
                reader.setFirstname(firstname);
                reader.setLastname(lastname);
                reader.setPhone(phone);
                readerFacade.create(reader);
                User user = new User();
                user.setLogin(login);
                user.setPassword(password1);
                user.setReader(reader);
                userFacade.create(user);
                
                Role readerRole = roleFacade.findByRoleName("READER");
                if(readerRole == null){
                    request.setAttribute("firstname", firstname);
                    request.setAttribute("lastname", lastname);
                    request.setAttribute("phone", phone);
                    request.setAttribute("login", login);
                    request.setAttribute("info", "Не найдена роль! Если ошибка повторится, обратитесь к разаработчику :)");
                    request.getRequestDispatcher("/showRegistration").forward(request, response);
                    break;
                }
                UserRoles userRoles = new UserRoles();
                userRoles.setRole(readerRole);
                userRoles.setUser(user);
                userRolesFacade.create(userRoles);
                request.setAttribute("info", "Добавлен новый пользователь");
                request.getRequestDispatcher("/listBooks").forward(request, response);
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
