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

    private String groupPropertyName;
    private UUID groupPropertyId;

    private String productName;
    private UUID productId;

    public PropertyValue toEntity(Product product, GroupProperty groupProperty) {
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setUuid(this.id != null ? this.id : UUID.randomUUID());
        propertyValue.setValue(this.value);
        if (this.groupPropertyId != null && groupProperty != null) {
            propertyValue.setGroupProperty(groupProperty); // ست کردن parent از بیرون
        }
        if (this.productId != null && product != null) {
            propertyValue.setProduct(product);
        }

//        propertyValue.setProduct(product);
//        propertyValue.setGroupProperty(groupProperty);
        return propertyValue;
    }

}
