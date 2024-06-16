package model.dto;

import lombok.Builder;

@Builder
public record OrderDto(
        Integer id,
        String orderName,
        String orderDescription,
        CustomerDto customer
) {
}