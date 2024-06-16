package model.service;

import model.dto.OrderDto;
import model.entity.Order;

import java.util.List;

public interface OrderService {


    List<OrderDto> queryAllOrders();

    void addNewOrder(Order order);

    void updateOrderById(Integer id);

    void deleteOrderById(Integer id);
}