package dao.impl;

import dao.ProductDAO;
import exception.DAOException;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAOImpl implements ProductDAO {
    private final String URL = "jdbc:mysql://localhost:3306/shop";
    private final String USER = "root";
    private final String PASSWORD = "12345";

    @Override
    public List<Product> getAllProducts() throws DAOException {
        List<Product> listOfProducts = new ArrayList<>();
        final String selectSql = "SELECT product_id,product_name,price,brand,image FROM products ;";
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setBrand(resultSet.getString(4));
                product.setImage(resultSet.getString(5));
                listOfProducts.add(product);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot get all products", ex);
        } finally {
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (resultSet!=null){
                    resultSet.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listOfProducts;

    }

    @Override
    public Product getProductById(String productId) throws DAOException {
        String selectSql = "SELECT * FROM products where product_id = '" + productId + "';";
        Product product = new Product();
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(3));
                product.setBrand(resultSet.getString(4));
                product.setCategoryId(resultSet.getInt(5));
                product.setImage(resultSet.getString(6));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot get product by id", ex);
        } finally {
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (resultSet!=null){
                    resultSet.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    @Override
    public boolean addProduct(Product product) throws DAOException {
        String insertSql = "INSERT INTO products (product_name,price,brand,category_id) VALUES (?,?,?,?);";
        int addedRows;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(insertSql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getBrand());
            statement.setLong(4, product.getCategoryId());
            addedRows = statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot add product", ex);
        } finally {
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return addedRows != 0;
    }

    @Override
    public boolean updateProduct(Product product, String productId) throws DAOException {

        String updateSql = "UPDATE products SET product_name = ?,price = ?,brand = ?,category_id = ? WHERE product_id =" + productId + ";";
        int affectedRows;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(updateSql);
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getBrand());
            statement.setInt(4, (int) product.getCategoryId());
            affectedRows = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot update product", ex);
        } finally {
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows != 0;
    }

    @Override
    public boolean deleteProduct(String productId) throws DAOException{

        String deleteSql = "DELETE FROM products WHERE product_id = " + productId + ";";
        int affectedRows;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.prepareStatement(deleteSql);
            affectedRows = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            throw new DAOException("Cannot delete product", ex);
        } finally {
            try {
                if (statement!=null) {
                    statement.close();
                }
                if (connection!=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows != 0;
    }
}


