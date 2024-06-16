package model.service;

import model.dto.ProductDto;

import java.util.List;

public interface ProductService {
    boolean addProduct(ProductDto productDto);
    boolean updateProduct(ProductDto productDto);
    boolean deleteProduct(int productId);
    ProductDto getProductById(int productId);
    List<ProductDto> getAllProducts();
    void clearAllProducts();
}
