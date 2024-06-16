package model.dao;

import model.entity.Order;

import java.util.List;

public interface OrderDao {
    int addNewOrder(Order order);
    int updateOrder(Integer id);
    int deleteOrderById(Integer id);
    List<Order> queryAllOrders();
    Order searchOrderById(Integer id);
}