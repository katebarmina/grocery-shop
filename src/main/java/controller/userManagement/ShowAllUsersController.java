package controller.userManagement;

import dao.impl.UserDAOImpl;
import models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllUsers")
public class ShowAllUsersController extends HttpServlet {
    private final UserDAOImpl userDaoImpl = new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   List<User> users = userDaoImpl.getAllUsers();
   request.setAttribute("users",users);
   request.getRequestDispatcher("/showAllUsers.jsp").forward(request,response);
    }

}
