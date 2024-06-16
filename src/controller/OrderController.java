package controller;

import model.dto.OrderDto;
import model.service.OrderService;
import model.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    private final OrderService orderService;

    public OrderController() {
        this.orderService = new OrderServiceImpl();
    }

    public boolean addOrder(OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    public boolean updateOrder(OrderDto orderDto) {
        return orderService.updateOrder(orderDto);
    }

    public boolean deleteOrder(int orderId) {
        return orderService.deleteOrder(orderId);
    }

    public OrderDto getOrderById(int orderId) {
        return orderService.getOrderById(orderId);
    }

    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void clearAllOrders() {
        orderService.clearAllOrders();
    }
}
