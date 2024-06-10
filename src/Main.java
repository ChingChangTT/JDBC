import model.dao.CustomerDaoImpl;
import model.entity.Customer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CustomerDaoImpl customerDao = new CustomerDaoImpl();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add a new customer");
            System.out.println("2. Update a customer by ID");
            System.out.println("3. Delete a customer by ID");
            System.out.println("4. Query all customers");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter customer ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter customer name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter customer email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter customer password:");
                    String password = scanner.nextLine();

                    System.out.println("Is customer deleted (true/false):");
                    boolean isDeleted = scanner.nextBoolean();
                    scanner.nextLine();

                    Date createDate = Date.valueOf(LocalDate.now());

                    Customer newCustomer = new Customer(id, name, email, password, isDeleted, createDate);
                    int addResult = customerDao.addNewCustomer(newCustomer);

                    if (addResult > 0) {
                        System.out.println("Customer added successfully.");
                    } else {
                        System.out.println("Failed to add customer.");
                    }
                }
                case 2 -> {
                    System.out.println("Enter customer ID to update:");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter new customer name:");
                    String name = scanner.nextLine();

                    System.out.println("Enter new customer email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter new customer password:");
                    String password = scanner.nextLine();

                    System.out.println("Is customer deleted (true/false):");
                    boolean isDeleted = scanner.nextBoolean();
                    scanner.nextLine(); // Consume newline

                    Date createDate = Date.valueOf(LocalDate.now());

                    Customer updatedCustomer = new Customer(id, name, email, password, isDeleted, createDate);
                    int updateResult = customerDao.updateCustomerById(id, updatedCustomer);

                    if (updateResult > 0) {
                        System.out.println("Customer updated successfully.");
                    } else {
                        System.out.println("Failed to update customer.");
                    }
                }
                case 3 -> {
                    System.out.println("Enter customer ID to delete:");
                    int id = scanner.nextInt();
                    int deleteResult = customerDao.deleteCustomerById(id);

                    if (deleteResult > 0) {
                        System.out.println("Customer deleted successfully.");
                    } else {
                        System.out.println("Failed to delete customer.");
                    }
                }
                case 4 -> {
                    List<Customer> customers = customerDao.queryAllCustomers();
                    System.out.println("List of all customers:");
                    for (Customer customer : customers) {
                        System.out.println(customer);
                    }
                }
                case 5 -> {
                    exit = true;
                    System.out.println("Exiting...");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}
