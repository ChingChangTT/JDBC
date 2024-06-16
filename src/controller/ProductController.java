package controller;

import model.dto.ProductDto;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController() {
        this.productService = new ProductServiceImpl();
    }

    public boolean addProduct(ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    public boolean updateProduct(ProductDto productDto) {
        return productService.updateProduct(productDto);
    }

    public boolean deleteProduct(int productId) {
        return productService.deleteProduct(productId);
    }

    public ProductDto getProductById(int productId) {
        return productService.getProductById(productId);
    }

    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    public void clearAllProducts() {
        productService.clearAllProducts();
    }
}
