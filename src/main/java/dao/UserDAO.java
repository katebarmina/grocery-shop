package dao;

import exception.DAOException;
import models.User;
import java.util.List;

public interface UserDAO {

    boolean isAdmin(User user);

    boolean registerUser(User user) throws DAOException;

    boolean IsRegistered(User user) throws DAOException;

    boolean checkPassword(User user) throws DAOException;

    long getUsersId(User user);

    boolean deleteUser(String userId) throws DAOException;

    List<User> getAllUsers() throws DAOException;
}
