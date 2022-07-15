package servlets;

import dao.UserDAO;
import models.Role;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "registrationServlet", value = "/registrationServlet")
public class RegistrationServlet extends HttpServlet {
    private UserDAO dao = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     response.setContentType("text/html");

     String email = request.getParameter("email");
     String password = request.getParameter("password");

        User user = new User(email,password,Role.CLIENT);
        dao.registerUser(user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/welcome.jsp");
        dispatcher.forward(request,response);
    }
}
