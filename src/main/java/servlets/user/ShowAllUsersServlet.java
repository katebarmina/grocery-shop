package servlets.user;

import models.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/showAllUsers")
public class ShowAllUsersServlet extends HttpServlet {
  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    List<User> users = userService.getAll();
    request.setAttribute("users", users);
    request
        .getRequestDispatcher(request.getContextPath() + "/showAllUsers.jsp")
        .forward(request, response);
  }
}
