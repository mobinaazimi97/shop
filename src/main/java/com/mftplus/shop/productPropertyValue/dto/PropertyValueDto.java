package com.mftplus.shop.productPropertyValue.dto;

import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.product.Product;
import com.mftplus.shop.productPropertyValue.PropertyValue;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class PropertyValueDto {

    private UUID id;
    private String value;
    private UUID groupPropertyId;
    private UUID productId;
}
