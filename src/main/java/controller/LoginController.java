package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import models.Role;
import models.User;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet("/login")
public class LoginController extends HttpServlet {

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("email");
        String password = request.getParameter("password");
        User newUser = new User();
        newUser.setEmail(username);
        newUser.setPassword(password);
        if (userDAO.IsRegistered(newUser) & userDAO.checkPassword(newUser)){
            newUser.setId(userDAO.getUsersId(newUser));
            if (userDAO.isAdmin(newUser)){
                newUser.setRole(Role.ADMIN);
            }else {
                newUser.setRole(Role.CLIENT);
            }
            HttpSession session = request.getSession();
            session.setAttribute("user",newUser);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else {
            int incorrectLog = 1;
            request.setAttribute("incorrectLog",incorrectLog);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
