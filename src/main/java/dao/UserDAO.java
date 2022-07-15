package dao;


import models.Role;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO  {

    private final String ADMIN_EMAIL = "admin@gmail.com";
    private final String ADMIN_PASSWORD = "12345";

    public boolean isAdmin(User user){
        if (user.getEmail().equals(ADMIN_EMAIL) && user.getPassword().equals(ADMIN_PASSWORD)){
            return true;
        }
        return false;
    }

   public boolean registerUser(User user){
       int addedRows = 0;
       final String insertUser = "INSERT INTO users (email,password,user_role) VALUES (?,?,?);";
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
           PreparedStatement statement = connection.prepareStatement(insertUser);
           statement.setString(1,user.getEmail());
           statement.setString(2,user.getPassword());
           statement.setString(3, String.valueOf(user.getRole()));
           addedRows=statement.executeUpdate();

           connection.close();

       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
     return addedRows!=0;
   }

   public boolean IsRegistered(User user){
       final String searchForUser= "SELECT * FROM users WHERE email = "+ "'"+user.getEmail()+"';";
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
           PreparedStatement statement = connection.prepareStatement(searchForUser);
           ResultSet resultSet = statement.executeQuery();
           if (resultSet.next() == false){
               return false;
           }
       }catch (SQLException | ClassNotFoundException ex){
           throw new RuntimeException(ex);
       }
      return true;
   }

   public long getUsersId(User user){
       String findUserId = "SELECT user_id FROM users where email = '"+user.getEmail()+"';";
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
           PreparedStatement statement = connection.prepareStatement(findUserId);
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
              return resultSet.getInt(1);
           }
       }catch (SQLException | ClassNotFoundException ex){
           throw new RuntimeException(ex);
       }
      return -1;
   }

   public boolean deleteUser(String userId){

        String deleteUser = "DELETE FROM users WHERE user_id = "+userId+";";
        int removedRows = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
            PreparedStatement statement = connection.prepareStatement(deleteUser);
             removedRows = statement.executeUpdate(deleteUser);
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return removedRows != 0;
   }

   public List<User> getAllUsers(){
        String getAllUsers = "SELECT * FROM users;";
        List<User> allUsers = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
            PreparedStatement statement = connection.prepareStatement(getAllUsers);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setEmail(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(Role.valueOf(resultSet.getString(4)));
                allUsers.add(user);
            }
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return allUsers;
   }



}
