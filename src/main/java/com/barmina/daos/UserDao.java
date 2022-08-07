package com.barmina.daos;

import com.barmina.models.Role;
import com.barmina.models.User;

import java.util.List;

public interface UserDao {

  void insertUser(User user) throws DaoException;

  boolean selectByEmail(User user) throws DaoException;

  boolean checkPassword(User user) throws DaoException;

  User getUser(User user);

  void delete(Long userId) throws DaoException;

  List<User> getAll() throws DaoException;

  void updateRole(Long userId, Role role) throws DaoException;
}
