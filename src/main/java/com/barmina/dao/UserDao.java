package com.barmina.dao;

import com.barmina.models.Role;
import com.barmina.models.User;

import java.util.List;

public interface UserDao {

  void register(User user) throws DaoException;

  boolean isRegistered(User user) throws DaoException;

  boolean checkPassword(User user) throws DaoException;

  User getUser(User user);

  void delete(String userId) throws DaoException;

  List<User> getAll() throws DaoException;

  void updateRole(String userId, Role role) throws DaoException;
}
