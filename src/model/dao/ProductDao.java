package model.dao;

import model.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> queryAllProducts();
    int addNewProduct(Product product);
    int updateProduct(Integer id);
    int deleteProduct(Integer id);
    Product searchProductById(Integer id);
}
