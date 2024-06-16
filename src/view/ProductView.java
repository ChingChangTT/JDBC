package view;

import controller.ProductController;
import excaption.ExceptionHandling;
import model.dto.ProductDto;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final ProductController productController = new ProductController();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("Product Management System");
            System.out.println("1. View All Products");
            System.out.println("2. Add New Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewAllProducts();
                    break;
                case 2:
                    addNewProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewAllProducts() {
        try {
            List<ProductDto> products = productController.queryAllProducts();
            if (products == null || products.isEmpty()) {
                System.out.println("No products found.");
            } else {
                System.out.println("Product List:");
                for (ProductDto product : products) {
                    System.out.println(product);
                }
            }
        } catch (ExceptionHandling e) {
            System.out.println("Error fetching product list: " + e.getMessage());
        }
    }

    private void addNewProduct() {
        try {
            productController.addNewProduct();
        } catch (ExceptionHandling e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    private void updateProduct() {
        try {
            productController.updateProduct();
        } catch (ExceptionHandling e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        productController.deleteProduct();
    }

}
