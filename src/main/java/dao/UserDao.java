package dao;

import models.User;
import java.util.List;

public interface UserDao {

    boolean isAdmin(User user);

    void register(User user) throws DaoException;

    boolean IsRegistered(User user) throws DaoException;

    boolean checkPassword(User user) throws DaoException;

    Long getId(User user);

    void delete(String userId) throws DaoException;

    List<User> getAll() throws DaoException;
}
