import view.CustomerView;
import view.OrderView;
import view.ProductView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1.Customer  Management");
            System.out.println("2. Order Management");
            System.out.println("3.Product  Management");
            System.out.println("4. Exit...");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    CustomerView cus = new CustomerView();
                    cus.showMenu();
                    break;
                case 2:
                    OrderView order = new OrderView();
                    order.showMenu();
                    break;
                case 3:
                    ProductView pro= new ProductView();
                    pro.showMenu();
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
