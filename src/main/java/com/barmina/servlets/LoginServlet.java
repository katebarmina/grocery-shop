package com.barmina.servlets;

import com.barmina.models.User;
import com.barmina.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

  private final UserService userService = new UserService();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.getRequestDispatcher("/login.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if (userService.isRegistered(email) && userService.isPasswordCorrect(password, email)) {
      User newUser = userService.login(email);
        HttpSession session = request.getSession();
        session.setAttribute("user", newUser);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    } else {
      int incorrectLog = 1;
      request.setAttribute("incorrectLog", incorrectLog);
      request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
  }
}
