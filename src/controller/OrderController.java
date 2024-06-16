package controller;
import excaption.ExceptionHandling;
import model.dto.OrderDto;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;
import model.service.OrderService;
import model.service.OrderServiceImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class OrderController {
    private static final OrderService orderService = new OrderServiceImpl();

    public List<OrderDto> queryAllOrders() throws ExceptionHandling {
        return orderService.queryAllOrders();
    }

    public void addNewOrder() {
        System.out.println("Order Name: ");
        String orderName = new Scanner(System.in).nextLine();
        System.out.println("Order Description: ");
        String orderDescription = new Scanner(System.in).nextLine();
        System.out.println("Customer ID: ");
        int customerId = new Scanner(System.in).nextInt();
        System.out.println("Product ID: ");
        int productId = new Scanner(System.in).nextInt();
        new OrderServiceImpl().addNewOrder(Order.builder()
                .id(1)
                .orderName(orderName)
                .orderDescription(orderDescription)
                .orderAt(Date.valueOf(LocalDate.now()))
                .customer(Customer.builder()
                        .id(customerId)
                        .build())
                .productList(new ArrayList<>(
                        List.of(Product.builder()
                                .id(productId)
                                .build())
                ))
                .build());
    }

    public void deleteOrder(){
        System.out.println("Order ID: ");
        new OrderServiceImpl().deleteOrderById(new Scanner(System.in).nextInt());
    }

    public void updateOrder(){
        System.out.println("Order ID: ");
        new OrderServiceImpl().updateOrderById(new Scanner(System.in).nextInt());
    }
}
