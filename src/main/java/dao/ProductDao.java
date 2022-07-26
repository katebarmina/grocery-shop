package dao;

import models.Product;
import java.util.List;

public interface ProductDao {

    List<Product> getAll() throws DaoException;

    Product getById(String productId) throws DaoException;

    void add(Product product) throws DaoException;

    void update(Product product, String productId) throws DaoException;

    void delete(String productId) throws DaoException;

}
