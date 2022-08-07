package com.barmina.servlets.user;

import com.barmina.models.Role;
import com.barmina.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateRole")
public class UpdateRoleServlet extends HttpServlet {
  private final UserService service = new UserService();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    Long userId = Long.valueOf(request.getParameter("userId"));
    String role = request.getParameter("role");
    service.updateRole(userId, Role.valueOf(role));
    response.sendRedirect(request.getContextPath() + "/showAllUsers");
  }
}
