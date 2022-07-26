package service;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import models.User;

import java.util.List;

public class UserService {
  private final UserDao dao = new UserDaoImpl();

  public void delete(String userId) {
    dao.delete(userId);
  }

  public List<User> getAll() {
    return dao.getAll();
  }

  public Long getId(User user) {
    return dao.getId(user);
  }

  public boolean isAdmin(User user) {
    return dao.isAdmin(user);
  }

  public void register(User user) {
    dao.register(user);
  }

  public boolean isRegistered(User user) {
    return dao.IsRegistered(user);
  }

  public boolean passwordIsCorrect(User user) {
    return dao.checkPassword(user);
  }
}
