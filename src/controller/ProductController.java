package controller;

import excaption.ExceptionHandling;
import model.dto.ProductDto;
import model.entity.Product;
import model.service.ProductService;
import model.service.ProductServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ProductController {
    private final ProductService productService = new ProductServiceImpl();

    public List<ProductDto> queryAllProducts() throws ExceptionHandling {
        return productService.queryAllProducts();
    }

    public void addNewProduct() throws ExceptionHandling {
        try {
            System.out.println("========| Adding new product |=======");
            System.out.print("Enter product name: ");
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            System.out.print("Enter product code: ");
            String code = scanner.nextLine();
            System.out.println("--------------------------------------");
            System.out.println("[*] Product Imported Date");
            System.out.print("Enter year (number): ");
            int importedYear = scanner.nextInt();
            System.out.print("Enter month (number): ");
            int importedMonth = scanner.nextInt();
            System.out.print("Enter day (number): ");
            int importedDay = scanner.nextInt();
            System.out.println("--------------------------------------");
            System.out.println("[*] Product Expired Date");
            System.out.print("Enter year (number): ");
            int expiredYear = scanner.nextInt();
            System.out.print("Enter month (number): ");
            int expiredMonth = scanner.nextInt();
            System.out.print("Enter day (number): ");
            int expiredDay = scanner.nextInt();
            System.out.print("Enter product description: ");
            String description = new Scanner(System.in).nextLine();
            productService.addNewProduct(Product.builder()
                    .id(new Random().nextInt(10000))
                    .productName(name)
                    .prodcutCode(code)
                    .isDeleted(false)
                    .importedDate(Date.valueOf(LocalDate.of(importedYear, importedMonth, importedDay)))
                    .expiredDate(Date.valueOf(LocalDate.of(expiredYear, expiredMonth, expiredDay)))
                    .productDescription(description)
                    .build());
        }catch (InputMismatchException e){
            System.out.println("Invalid Input !");
        }
    }

    public void updateProduct() throws ExceptionHandling {
        System.out.println("Product ID: ");
        productService.updateProductById(new Scanner(System.in).nextInt());
    }

    public void deleteProduct(){
        System.out.println("Product ID: ");
        productService.deleteProductById(new Scanner(System.in).nextInt());
    }

}
