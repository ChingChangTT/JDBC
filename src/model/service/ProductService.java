package model.service;

import excaption.ExceptionHandling;

import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> queryAllProducts() throws ExceptionHandling;
    void addNewProduct(Product product) throws ExceptionHandling;
    void updateProductById(Integer id) throws ExceptionHandling;
    void deleteProductById(Integer id);
}
