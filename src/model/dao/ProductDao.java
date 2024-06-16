package model.dao;

import model.entity.Product;

import java.util.List;

public interface ProductDao {
    int addProduct(Product product);
    int updateProduct(Product product);
    void deleteProduct(int productId);
    Product getProductById(int productId);
    List<Product> getAllProducts();
    void clearAllProducts();
}
