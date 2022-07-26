package servlets.user;

import service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/manageShop/deleteUser")
public class DeleteUserServlet extends HttpServlet {
  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String userId = request.getParameter("userId");
    userService.delete(userId);
    response.sendRedirect(request.getContextPath() + "/showAllUsers");
  }
}
