package view;

import controller.OrderController;
import model.dto.OrderDto;
import model.entity.Customer;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final OrderController orderController;
    private final Scanner scanner;

    public OrderView() {
        this.orderController = new OrderController();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("1. Add Order");
            System.out.println("2. Update Order");
            System.out.println("3. Delete Order");
            System.out.println("4. Get Order By ID");
            System.out.println("5. Get All Orders");
            System.out.println("6. Clear All Orders");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    updateOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 4:
                    getOrderById();
                    break;
                case 5:
                    getAllOrders();
                    break;
                case 6:
                    clearAllOrders();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addOrder() {
        OrderDto orderDto = getOrderDetails();
        boolean success = orderController.addOrder(orderDto);
        System.out.println(success ? "Order added successfully." : "Failed to add order.");
    }

    private void updateOrder() {
        System.out.print("Enter Order ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        OrderDto existingOrder = orderController.getOrderById(id);
        if (existingOrder == null) {
            System.out.println("Order not found.");
            return;
        }

        OrderDto orderDto = getOrderDetails();
        orderDto.setId(id);
        boolean success = orderController.updateOrder(orderDto);
        System.out.println(success ? "Order updated successfully." : "Failed to update order.");
    }

    private void deleteOrder() {
        System.out.print("Enter Order ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        boolean success = orderController.deleteOrder(id);
        System.out.println(success ? "Order deleted successfully." : "Failed to delete order.");
    }

    private void getOrderById() {
        System.out.print("Enter Order ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        OrderDto orderDto = orderController.getOrderById(id);
        if (orderDto != null) {
            System.out.println(orderDto);
        } else {
            System.out.println("Order not found.");
        }
    }

    private void getAllOrders() {
        List<OrderDto> orders = orderController.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (OrderDto order : orders) {
                System.out.println(order);
            }
        }
    }

    private void clearAllOrders() {
        orderController.clearAllOrders();
        System.out.println("All orders cleared.");
    }

    private OrderDto getOrderDetails() {
        System.out.print("Enter Order Name: ");
        String orderName = scanner.nextLine();

        System.out.print("Enter Order Description: ");
        String orderDescription = scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Customer customer = new Customer();
        customer.setId(customerId);

        return OrderDto.builder()
                .order_name(orderName)
                .orderDescription(orderDescription)
                .orderdAt(new Date())
                .customer(customer)
                .build();
    }
}
