package view;

import controller.CustomerController;
import excaption.ExceptionHandling;
import model.dto.CustomerDto;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private final CustomerController customerController = new CustomerController();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("Customer Management System");
            System.out.println("1. View All Customers");
            System.out.println("2. Add New Customer");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewAllCustomers();
                    break;
                case 2:
                    addNewCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewAllCustomers() {
        try {
            List<CustomerDto> customers = customerController.queryAllCustomers();
            if (customers == null || customers.isEmpty()) {
                System.out.println("No customers found.");
            } else {
                System.out.println("Customer List:");
                for (CustomerDto customer : customers) {
                    System.out.println(customer);
                }
            }
        } catch (ExceptionHandling e) {
            System.out.println("Error fetching customer list: " + e.getMessage());
        }
    }

    private void addNewCustomer() {
        customerController.addNewCustomer();
    }

    private void updateCustomer() {
        customerController.updateCustomer();
    }

    private void deleteCustomer() {
        customerController.deleteCustomer();
    }

}
