package dao;

import exception.DAOException;
import models.Product;
import java.util.List;

public interface ProductDAO {

    List<Product> getAllProducts() throws DAOException;

    Product getProductById(String productId) throws DAOException;

    boolean addProduct(Product product) throws DAOException;

    boolean updateProduct(Product product, String productId) throws DAOException;

    boolean deleteProduct(String productId) throws DAOException;

}
