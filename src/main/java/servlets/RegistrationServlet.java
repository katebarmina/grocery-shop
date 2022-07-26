package servlets;

import models.Role;
import models.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher("/registration.jsp");
    dispatcher.forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html");

    HttpSession session = request.getSession();
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = new User(email, password, Role.CLIENT);
    userService.register(user);
    user.setId(userService.getId(user));
    session.setAttribute("user", user);
    response.sendRedirect(request.getContextPath() + "/index.jsp");
  }
}
