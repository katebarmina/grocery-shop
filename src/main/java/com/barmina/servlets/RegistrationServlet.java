package com.barmina.servlets;

import com.barmina.models.User;
import com.barmina.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    userService.register(email, password);
    User user = userService.login(email);
    session.setAttribute("user", user);
    response.sendRedirect(request.getContextPath() + "/index.jsp");
  }
}
