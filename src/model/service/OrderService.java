package model.service;

import model.dto.OrderDto;

import java.util.List;

public interface OrderService {
    boolean addOrder(OrderDto orderDto);
    boolean updateOrder(OrderDto orderDto);
    boolean deleteOrder(int orderId);
    OrderDto getOrderById(int orderId);
    List<OrderDto> getAllOrders();
    void clearAllOrders();
}
