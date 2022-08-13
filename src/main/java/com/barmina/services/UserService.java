package com.barmina.services;

import com.barmina.daos.UserDao;
import com.barmina.daos.impl.UserDaoImpl;
import com.barmina.models.Role;
import com.barmina.models.User;

import java.util.List;

public class UserService {

  private final PasswordHasher hasher = new PasswordHasher();
  private final UserDao dao = new UserDaoImpl();

  public void delete(Long userId) {
    dao.delete(userId);
  }

  public List<User> getAll() {
    return dao.getAll();
  }

  public void register(String email, String password) {
    User user = new User();
    byte[] salt = hasher.getSalt();
    user.setEmail(email);
    user.setRole(Role.CLIENT);
    user.setPassword(hasher.hashPassword(password, salt));
    user.setSalt(salt);
    dao.insertUser(user);
  }

  public boolean isRegistered(String email) {
    return dao.getByEmail(email).isPresent();
  }

  public User login(String email) {
    return dao.getByEmail(email).get();
  }

  public boolean isPasswordCorrect(String password, String email) {
    User user = dao.getByEmail(email).get();
    user.setPassword(password);
    byte[] salt = user.getSalt();
    user.setPassword(hasher.hashPassword(user.getPassword(), salt));
    return dao.checkPassword(user);
  }

  public void updateRole(Long userId, Role role) {
    dao.updateRole(userId, role);
  }
}
