package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import models.Role;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(email, password, Role.CLIENT);
        userDAO.registerUser(user);
        user.setId(userDAO.getUsersId(user));
        session.setAttribute("user",user);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
