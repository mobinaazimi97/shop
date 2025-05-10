package com.mftplus.shop.product.dto;

import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductDto {
    private UUID id;
    private String productName;
    private Float price;
    private String description;
    private String code;
    private UUID productGroupId;
    private String productGroupTitle; // عنوان گروه محصول جدید
    private List<PropertyValueDto> propertyValues; // لیست از PropertyValueDto
}
