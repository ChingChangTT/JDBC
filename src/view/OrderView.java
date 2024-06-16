package view;

import controller.OrderController;
import excaption.ExceptionHandling;
import model.dto.OrderDto;

import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final OrderController orderController = new OrderController();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("Order Management System");
            System.out.println("1. View All Orders");
            System.out.println("2. Add New Order");
            System.out.println("3. Update Order");
            System.out.println("4. Delete Order");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    viewAllOrders();
                    break;
                case 2:
                    addNewOrder();
                    break;
                case 3:
                    updateOrder();
                    break;
                case 4:
                    deleteOrder();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewAllOrders() {
        try {
            List<OrderDto> orders = orderController.queryAllOrders();
            if (orders == null || orders.isEmpty()) {
                System.out.println("No orders found.");
            } else {
                System.out.println("Order List:");
                for (OrderDto order : orders) {
                    System.out.println(order);
                }
            }
        } catch (ExceptionHandling e) {
            System.out.println("Error fetching order list: " + e.getMessage());
        }
    }

    private void addNewOrder() {
        orderController.addNewOrder();
    }

    private void updateOrder() {
        orderController.updateOrder();
    }

    private void deleteOrder() {
        orderController.deleteOrder();
    }
}
