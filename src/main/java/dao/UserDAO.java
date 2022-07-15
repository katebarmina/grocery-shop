package dao;


import models.User;

import java.sql.*;


public class UserDAO  {

    private final String ADMIN_EMAIL = "admin@gmail.com";
    private final String ADMIN_PASSWORD = "12345";

    public boolean isAdmin(User user){
        if (user.getEmail().equals(ADMIN_EMAIL) && user.getPassword().equals(ADMIN_PASSWORD)){
            return true;
        }
        return false;
    }

   public int registerUser(User user){
       int result;
       final String INSERT_USER = "INSERT INTO users (email,password,user_role) VALUES (?,?,?);";
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
           PreparedStatement statement = connection.prepareStatement(INSERT_USER);
           statement.setString(1,user.getEmail());
           statement.setString(2,user.getPassword());
           statement.setString(3, String.valueOf(user.getRole()));
           result=statement.executeUpdate();

           connection.close();

       } catch (SQLException | ClassNotFoundException e) {
           throw new RuntimeException(e);
       }
     return result;
   }

   public boolean IsRegistered(User user){
       final String SEARCH= "SELECT * FROM users WHERE email = "+ "'"+user.getEmail()+"';";
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
           PreparedStatement statement = connection.prepareStatement(SEARCH);
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
       String FIND_USER = "SELECT user_id FROM users where email = '"+user.getEmail()+"';";
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
           PreparedStatement statement = connection.prepareStatement(FIND_USER);
           ResultSet resultSet = statement.executeQuery();
           while (resultSet.next()){
              return resultSet.getInt(1);
           }
       }catch (SQLException | ClassNotFoundException ex){
           throw new RuntimeException(ex);
       }
      return -1;
   }



}
