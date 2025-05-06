package com.mftplus.shop.product.mapper;

import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "uuid", target = "id")
    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productGroup.uuId", target = "productGroupId")
    ProductDto toDto(Product product);


    @InheritInverseConfiguration
    @Mapping(target = "productGroup", ignore = true)
        // چون توی toEntity باید جداگانه تنظیم بشه
    Product toEntity(ProductDto dto);

}
