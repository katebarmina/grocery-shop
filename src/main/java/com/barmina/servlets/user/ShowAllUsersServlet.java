package com.barmina.servlets.user;

import com.barmina.models.User;
import com.barmina.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
