package servlets.userManagement;

import dao.UserDAO;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllUsers")
public class ShowAllUsersServlet extends HttpServlet {
    private final UserDAO userDao = new UserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   List<User> users = userDao.getAllUsers();
   request.setAttribute("users",users);
   request.getRequestDispatcher("/showAllUsers.jsp").forward(request,response);
    }

}
