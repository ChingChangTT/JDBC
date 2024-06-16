import view.CustomerView;
import view.OrderView;
import view.ProductView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Product Management");
            System.out.println("2. Order Management");
            System.out.println("3. Customer Management");
            System.out.println("4. Exit...");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ProductView productView = new ProductView();
                    productView.displayMenu();
                    break;
                case 2:
                    OrderView orderView = new OrderView();
                    orderView.displayMenu();
                    break;
                case 3:
                    CustomerView customerView = new CustomerView();
                    customerView.displayMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }
    }
}
