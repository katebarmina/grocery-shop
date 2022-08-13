package com.barmina.daos;

import com.barmina.models.Role;
import com.barmina.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

  void insertUser(User user) throws DaoException;

  Optional<User> getByEmail(String email) throws DaoException;

  boolean checkPassword(User user) throws DaoException;

  void delete(Long userId) throws DaoException;

  List<User> getAll() throws DaoException;

  void updateRole(Long userId, Role role) throws DaoException;
}
