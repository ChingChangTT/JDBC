package model.service;

import excaption.ExceptionHandling;
import mapper.ProductMapper;
import model.dao.ProductDao;
import model.dao.ProductDaoImpl;
import model.dto.ProductDto;
import model.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<ProductDto> queryAllProducts() {
        try {
            List<Product> products = productDao.queryAllProducts();
            if (!(products.isEmpty())) {
                return productDao.queryAllProducts().stream().map(ProductMapper::mapProductToProductDto).toList();
            }else {
                throw new ExceptionHandling("No Data !");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void addNewProduct(Product product) {
        try {
            if (productDao.addNewProduct(product) > 0) {
                throw new ExceptionHandling("Product Added Successfully !");
            }else {
                throw new ExceptionHandling("Cant add product !");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateProductById(Integer id) {
        try{
            if (productDao.updateProduct(id)>0){
                throw new ExceptionHandling("Product Updated Successfully !");
            }else {
                throw new ExceptionHandling("Cant update product !");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteProductById(Integer id) {
        try{
            if (productDao.deleteProduct(id)>0){
                throw new ExceptionHandling("Product Deleted Successfully !");
            }else {
                throw new ExceptionHandling("Cant delete product !");
            }
        }catch (ExceptionHandling e){
            System.out.println(e.getMessage());
        }
    }
}
