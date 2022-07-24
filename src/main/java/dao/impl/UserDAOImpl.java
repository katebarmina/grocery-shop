package dao.impl;

import dao.UserDAO;
import exception.DAOException;
import models.Role;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl implements UserDAO {

    private final String URL = "jdbc:mysql://localhost:3306/shop";
    private final String USER = "root";
    private final String PASSWORD = "12345";

    @Override
    public boolean isAdmin(User user) {
        String ADMIN_EMAIL = "admin@gmail.com";
        String ADMIN_PASSWORD = "12345";
        return user.getEmail().equals(ADMIN_EMAIL) && user.getPassword().equals(ADMIN_PASSWORD);
    }

    @Override
    public boolean registerUser(User user) throws DAOException {
        int addedRows;
        final String insertSql = "INSERT INTO users (email,password,user_role) VALUES (?,?,?);";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, String.valueOf(user.getRole()));
            addedRows = statement.executeUpdate();
        return addedRows!=0;
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot register user", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean IsRegistered(User user) throws DAOException {
        final String selectSql = "SELECT * FROM users WHERE email = " + "'" + user.getEmail() + "';";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot get find user with this email", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean checkPassword(User user) throws DAOException {
        final String checkUserPassword = "SELECT * FROM users WHERE email = " + "'" + user.getEmail() + "' and password = '" + user.getPassword() + "';";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(checkUserPassword);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot find user with this password", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public long getUsersId(User user) throws DAOException{
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String selectSql = "SELECT user_id FROM users where email = '" + user.getEmail() + "';";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
               return resultSet.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot get user by id", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    @Override
    public boolean deleteUser(String userId) throws DAOException {

        String deleteUser = "DELETE FROM users WHERE user_id = " + userId + ";";
        int removedRows;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(deleteUser);
            removedRows = statement.executeUpdate(deleteUser);
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot get user by id", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return removedRows != 0;
    }

    @Override
    public List<User> getAllUsers() throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String getAllUsers = "SELECT * FROM users;";
        List<User> allUsers = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(getAllUsers);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(Role.valueOf(resultSet.getString(4)));
                allUsers.add(user);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot get all users", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return allUsers;
    }
}
