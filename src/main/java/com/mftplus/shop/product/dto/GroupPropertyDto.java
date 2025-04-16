package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.GroupProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class GroupPropertyDto {
    private Long gPropertyId;
    private String name;
    private ProductPropertyValueDto productPropertyValueDto;

    public GroupProperty getGroupProperty() {
        return GroupProperty.builder()
                .id(gPropertyId)
                .name(name)
                .productPropertyValue(productPropertyValueDto.getProductPropertyValue())
                .build();

    }
}
