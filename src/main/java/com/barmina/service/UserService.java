package com.barmina.service;

import com.barmina.dao.UserDao;
import com.barmina.dao.impl.UserDaoImpl;
import com.barmina.models.Role;
import com.barmina.models.User;

import java.util.List;

public class UserService {

  private final PasswordHasher hasher = new PasswordHasher();
  private final UserDao dao = new UserDaoImpl();

  public void delete(String userId) {
    dao.delete(userId);
  }

  public List<User> getAll() {
    return dao.getAll();
  }

  public User getUser(User user) {
    return dao.getUser(user);
  }


  public void register(User user) {
    byte[] salt = hasher.getSalt();
    user.setPassword(hasher.hashPassword(user.getPassword(), salt));
    user.setSalt(salt);
    dao.register(user);
  }

  public boolean isRegistered(User user) {
    return dao.isRegistered(user);
  }

  public boolean passwordIsCorrect(User user) {
    byte[] salt = user.getSalt();
    user.setPassword(hasher.hashPassword(user.getPassword(), salt));
    return dao.checkPassword(user);
  }

  public void updateRole(String userId, Role role) {
    dao.updateRole(userId, role);
  }
}
