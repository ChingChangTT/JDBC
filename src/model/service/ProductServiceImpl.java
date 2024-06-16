package model.service;

import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl() {
        this.productDao = new ProductDaoImpl();
    }

    @Override
    public boolean addProduct(ProductDto productDto) {
        Product product = Product.builder()
                .productName(productDto.getProductName())
                .productCode(productDto.getProductCode())
                .productDescription(productDto.getProductDescription())
                .build();
        return productDao.addProduct(product) > 0;
    }

    @Override
    public boolean updateProduct(ProductDto productDto) {
        Product product = Product.builder()
                .id(productDto.getId())
                .productName(productDto.getProductName())
                .productCode(productDto.getProductCode())
                .productDescription(productDto.getProductDescription())
                .build();
        return productDao.updateProduct(product) > 0;
    }

    @Override
    public boolean deleteProduct(int productId) {
        productDao.deleteProduct(productId);
        return true;
    }

    @Override
    public ProductDto getProductById(int productId) {
        Product product = productDao.getProductById(productId);
        if (product != null) {
            return ProductDto.builder()
                    .id(product.getId())
                    .productName(product.getProductName())
                    .productCode(product.getProductCode())
                    .productDescription(product.getProductDescription())
                    .build();
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.getAllProducts();
        return products.stream().map(product -> ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productCode(product.getProductCode())
                .productDescription(product.getProductDescription())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void clearAllProducts() {
        productDao.clearAllProducts();
    }
}
