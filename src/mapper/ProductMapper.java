package mapper;

import model.dto.ProductDto;
import model.entity.Product;

public class ProductMapper {
    public static ProductDto mapProductToProductDto(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .productDescription(product.getProductDescription())
                .importDate(product.getImportedDate())
                .expiredDate(product.getExpiredDate())
                .build();
    }
}
