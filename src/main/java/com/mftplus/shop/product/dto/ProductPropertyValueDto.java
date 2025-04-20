package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.entity.ProductPropertyValue;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductPropertyValueDto {
    private Long id;
    private String name;
    private String value;


    public ProductPropertyValue getProductPropertyValue() {
        return ProductPropertyValue.builder()
                .id(id)
                .name(name)
                .value(value)
                .build();
    }
}
