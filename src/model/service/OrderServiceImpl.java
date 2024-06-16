package model.service;

import model.dao.OrderDao;
import model.dao.OrderDaoImpl;
import model.dto.OrderDto;
import model.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    @Override
    public boolean addOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        return orderDao.addOrder(order) > 0;
    }

    @Override
    public boolean updateOrder(OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        return orderDao.updateOrder(order) > 0;
    }

    @Override
    public boolean deleteOrder(int orderId) {
        orderDao.deleteOrder(orderId);
        return true;
    }

    @Override
    public OrderDto getOrderById(int orderId) {
        Order order = orderDao.getOrderById(orderId);
        return convertToDto(order);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDao.getAllOrders();
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void clearAllOrders() {
        orderDao.clearAllOrders();
    }

    private Order convertToEntity(OrderDto orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .order_name(orderDto.getOrder_name())
                .orderDescription(orderDto.getOrderDescription())
                .orderdAt(orderDto.getOrderdAt())
                .customer(orderDto.getCustomer())
                .build();
    }

    private OrderDto convertToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .order_name(order.getOrder_name())
                .orderDescription(order.getOrderDescription())
                .orderdAt(order.getOrderdAt())
                .customer(order.getCustomer())
                .build();
    }
}
