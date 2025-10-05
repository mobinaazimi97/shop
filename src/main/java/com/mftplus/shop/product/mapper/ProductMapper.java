package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.product.Product;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.productGroup.mapper.ProductGroupMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.*;

import java.util.List;


@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, ProductGroupMapper.class})
public interface ProductMapper {
    @Mapping(target = "productGroupId", source = "productGroup.id")
    @Mapping(target = "productGroupTitle", source = "productGroup.title")
    ProductDto toDto(Product product, @Context String entityType);

    Product toEntity(ProductDto productDto, @Context String entityType);

    List<ProductDto> toDtoList(List<Product> productList, @Context String entityType);

    List<Product> toEntityList(List<ProductDto> productDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(ProductDto productDto, @MappingTarget Product product, @Context String entityType);

}
