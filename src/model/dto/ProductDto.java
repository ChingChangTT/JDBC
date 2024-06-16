package model.dto;

import lombok.Builder;

import java.sql.Date;

@Builder
public record ProductDto(
        Integer id,
        String productName,
        Date importDate,
        Date expiredDate,
        String productDescription
) {
}
