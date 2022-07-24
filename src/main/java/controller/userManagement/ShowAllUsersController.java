package controller.userManagement;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllUsers")
public class ShowAllUsersController extends HttpServlet {
    private final UserDAO userDao = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher(request.getContextPath()+"/showAllUsers.jsp").forward(request, response);
    }

}
