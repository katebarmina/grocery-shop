package com.barmina.servlets.user;

import com.barmina.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
