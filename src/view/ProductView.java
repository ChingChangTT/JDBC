package view;

import controller.ProductController;
import model.dto.ProductDto;

import java.util.List;
import java.util.Scanner;

public class ProductView {
    private final ProductController productController;
    private final Scanner scanner;

    public ProductView() {
        this.productController = new ProductController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nProduct Management System");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Product");
            System.out.println("5. View All Products");
            System.out.println("6. Clear All Products");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    viewProduct();
                    break;
                case 5:
                    viewAllProducts();
                    break;
                case 6:
                    clearAllProducts();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        ProductDto productDto = ProductDto.builder()
                .productName(name)
                .productDescription(description)
                .build();

        if (productController.addProduct(productDto)) {
            System.out.println("Product added successfully.");
        } else {
            System.out.println("Failed to add product.");
        }
    }

    private void updateProduct() {
        int id;
        while (true) {
            System.out.print("Enter product ID to update: ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        ProductDto productDto = ProductDto.builder()
                .id(id)
                .productName(name)
                .build();

        if (productController.updateProduct(productDto)) {
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Failed to update product.");
        }
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = scanner.nextInt();

        if (productController.deleteProduct(id)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product.");
        }
    }

    private void viewProduct() {
        System.out.print("Enter product ID to view: ");
        int id = scanner.nextInt();

        ProductDto productDto = productController.getProductById(id);
        if (productDto != null) {
            System.out.println("Product Details:");
            System.out.println("ID: " + productDto.getId());
            System.out.println("Name: " + productDto.getProductName());
            System.out.println("Code: " + productDto.getProductCode());
            System.out.println("Description: " + productDto.getProductDescription());
        } else {
            System.out.println("Product not found.");
        }
    }

    private void viewAllProducts() {
        List<ProductDto> products = productController.getAllProducts();
        System.out.println("All Products:");
        for (ProductDto product : products) {
            System.out.println("ID: " + product.getId());
            System.out.println("Name: " + product.getProductName());
            System.out.println("Code: " + product.getProductCode());
            System.out.println("Description: " + product.getProductDescription());
            System.out.println("-----------");
        }
    }

    private void clearAllProducts() {
        productController.clearAllProducts();
        System.out.println("All products cleared.");
    }

}
