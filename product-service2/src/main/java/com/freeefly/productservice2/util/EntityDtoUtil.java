package com.freeefly.productservice2.util;

import com.freeefly.productservice2.dto.ProductDto;
import com.freeefly.productservice2.entity.Product;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setDescription(product.getDescription());
        dto.setId(product.getId());
        dto.setPrice(product.getPrice());
        return dto;
    }
    public static Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setDescription(dto.getDescription());
        product.setId(dto.getId());
        product.setPrice(dto.getPrice());
        return product;
    }

}
