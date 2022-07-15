package dao;

import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {

    public List<Product> showAllProducts() {
        List<Product> listOfProducts = new ArrayList<>();
        final String SHOW_PRODUCTS = "SELECT product_id,product_name,price,brand  FROM products ;";
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
            PreparedStatement statement = connection.prepareStatement(SHOW_PRODUCTS);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setBrand(resultSet.getString(4));
                listOfProducts.add(product);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }finally {
            try {
                if (resultSet!=null){
                    resultSet.close();
                }
                if (connection!=null){
                    connection.close();
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        return listOfProducts;

    }

    public Product getProductById(String productId){
        String FIND_USER = "SELECT * FROM products where product_id = '"+productId+"';";
        Product product = new Product();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop","root","12345");
            PreparedStatement statement = connection.prepareStatement(FIND_USER);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setBrand(resultSet.getString(4));
            }
        }catch (SQLException | ClassNotFoundException ex){
            throw new RuntimeException(ex);
        }
        return product;
    }

    public boolean addProduct(Product product){
        String addProduct = "INSERT INTO products (product_name,price,brand,category_id) VALUES (?,?,?,?);";
        int addedRows = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
            PreparedStatement statement = connection.prepareStatement(addProduct);
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.setString(3,product.getBrand());
            statement.setLong(4,product.getCategoryId());
            addedRows = statement.executeUpdate();

        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return addedRows != 0;
    }

    public boolean updateProduct(Product product){
        String updateProduct = "UPDATE products SET product_name = ?,price = ?,brand = ?,category_id = ?;";
         int affectedRows = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
            PreparedStatement statement = connection.prepareStatement(updateProduct);
            statement.setString(1,product.getName());
            statement.setDouble(2,product.getPrice());
            statement.setString(3,product.getBrand());
            statement.setLong(4,product.getCategoryId());
            affectedRows = statement.executeUpdate();
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return affectedRows!=0;
    }

    public boolean deleteProduct(String productId){
        String deleteProduct = "DELETE FROM products WHERE product_id = "+productId+";";
        int affectedRows = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "12345");
            PreparedStatement statement = connection.prepareStatement(deleteProduct);
            affectedRows = statement.executeUpdate();
        }catch (SQLException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return affectedRows!=0;
    }
    }


