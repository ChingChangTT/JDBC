package view;

import controller.CustomerController;
import model.dto.CustomerDto;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private final CustomerController customerController;
    private final Scanner scanner;

    public CustomerView() {
        this.customerController = new CustomerController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\nCustomer Management Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. Update Customer");
            System.out.println("3. Delete Customer");
            System.out.println("4. View Customer by ID");
            System.out.println("5. View All Customers");
            System.out.println("6. Clear All Customers");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    updateCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    viewCustomerById();
                    break;
                case 5:
                    viewAllCustomers();
                    break;
                case 6:
                    clearAllCustomers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter customer password: ");
        String password = scanner.nextLine();

        CustomerDto customerDto = CustomerDto.builder()
                .name(name)
                .email(email)
                .password(password)
                .isDeleted(false)
                .createDate(new java.util.Date())
                .build();

        boolean isSuccess = customerController.addCustomer(customerDto);
        if (isSuccess) {
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Failed to add customer.");
        }
    }

    private void updateCustomer() {
        System.out.print("Enter customer ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter new customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new customer email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new customer password: ");
        String password = scanner.nextLine();

        CustomerDto customerDto = CustomerDto.builder()
                .id(id)
                .name(name)
                .email(email)
                .password(password)
                .isDeleted(false)
                .createDate(new java.util.Date())
                .build();

        boolean isSuccess = customerController.updateCustomer(customerDto);
        if (isSuccess) {
            System.out.println("Customer updated successfully.");
        } else {
            System.out.println("Failed to update customer.");
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter customer ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        boolean isSuccess = customerController.deleteCustomer(id);
        if (isSuccess) {
            System.out.println("Customer deleted successfully.");
        } else {
            System.out.println("Failed to delete customer.");
        }
    }

    private void viewCustomerById() {
        System.out.print("Enter customer ID to view: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        CustomerDto customerDto = customerController.getCustomerById(id);
        if (customerDto != null) {
            System.out.println("Customer Details:");
            System.out.println("ID: " + customerDto.getId());
            System.out.println("Name: " + customerDto.getName());
            System.out.println("Email: " + customerDto.getEmail());
            System.out.println("Password: " + customerDto.getPassword());
            System.out.println("Is Deleted: " + customerDto.getIsDeleted());
            System.out.println("Create Date: " + customerDto.getCreateDate());
        } else {
            System.out.println("Customer not found.");
        }
    }

    private void viewAllCustomers() {
        List<CustomerDto> customers = customerController.getAllCustomers();
        if (!customers.isEmpty()) {
            System.out.println("All Customers:");
            for (CustomerDto customerDto : customers) {
                System.out.println("ID: " + customerDto.getId());
                System.out.println("Name: " + customerDto.getName());
                System.out.println("Email: " + customerDto.getEmail());
                System.out.println("Password: " + customerDto.getPassword());
                System.out.println("Is Deleted: " + customerDto.getIsDeleted());
                System.out.println("Create Date: " + customerDto.getCreateDate());
                System.out.println("----------");
            }
        } else {
            System.out.println("No customers found.");
        }
    }

    private void clearAllCustomers() {
        customerController.clearAllCustomers();
        System.out.println("All customers cleared.");
    }
}
