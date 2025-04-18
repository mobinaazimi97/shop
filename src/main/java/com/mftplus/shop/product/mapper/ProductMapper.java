package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface ProductMapper {
    ProductDto toDto(Product entity);

    Product toEntity(ProductDto dto);

    List<ProductDto> toDtoList(List<Product> list);

    List<Product> toEntityList(List<ProductDto> list);

}
