package model.dao;

import model.entity.Order;

import java.util.List;

public interface OrderDao {
    int addOrder(Order order);
    int updateOrder(Order order);
    void deleteOrder(int orderId);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
    void clearAllOrders();
}
