package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.product.entity.ProductPropertyValue;
import com.mftplus.shop.product.dto.ProductPropertyValueDto;
import org.mapstruct.Mapper;


@Mapper(config = CentralMapperConfig.class)
public interface ProductPropertyValueMapper extends BaseMapper<ProductPropertyValue, ProductPropertyValueDto> {
//    ProductPropertyValueDto toDto(ProductPropertyValue entity);
//
//    ProductPropertyValue toEntity(ProductPropertyValueDto dto);
//
//    List<ProductPropertyValueDto> toDtoList(List<ProductPropertyValue> list);

}
