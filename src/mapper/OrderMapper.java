package mapper;

import model.dto.CustomerDto;
import model.dto.OrderDto;
import model.entity.Order;

public class OrderMapper {
    public static OrderDto mapOrderToOrderDto(Order order) {
        if (order == null) {
            return null;
        }
        return OrderDto.builder()
                .id(order.getId())
                .orderName(order.getOrderName())
                .orderDescription(order.getOrderDescription())
                .customer(CustomerDto.builder()
                        .id(order.getCustomer().getId())
                        .name(order.getCustomer().getName())
                        .email(order.getCustomer().getEmail())
                        .bio(order.getCustomer().getBio())
                        .build())
                .build();
    }
}