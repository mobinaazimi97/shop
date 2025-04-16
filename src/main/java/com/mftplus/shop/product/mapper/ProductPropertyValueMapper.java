package com.mftplus.shop.product.mapper;

import com.mftplus.shop.product.ProductPropertyValue;
import com.mftplus.shop.product.dto.ProductPropertyValueDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductPropertyValueMapper {
    ProductPropertyValueDto toDto(ProductPropertyValue entity);

    ProductPropertyValue toEntity(ProductPropertyValueDto dto);

    List<ProductPropertyValueDto> toDtoList(List<ProductPropertyValue> list);

}
