package com.mftplus.shop.product.mapper;

import com.mftplus.shop.product.ProductGroup;
import com.mftplus.shop.product.dto.ProductGroupDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductGroupMapper {
    @Named("basicToEntity")
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "childList", ignore = true)
    ProductGroup toEntity(ProductGroupDto dto);

    @Named("basicToDto")
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "childList", ignore = true)
    ProductGroupDto toDto(ProductGroup entity);

    // === Full mapping with parent ===
    @Named("toEntityWithParent")
    @Mapping(target = "parent", qualifiedByName = "basicToEntity")
    @Mapping(target = "childList", ignore = true)
    ProductGroup toEntityWithParent(ProductGroupDto dto);

    @Named("toDtoWithParent")
    @Mapping(target = "parent", qualifiedByName = "basicToDto")
    @Mapping(target = "childList", ignore = true)
    ProductGroupDto toDtoWithParent(ProductGroup entity);

    // === Full mapping with children ===
    @Named("toDtoWithChildren")
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "childList", qualifiedByName = "toDtoWithChildren")
    ProductGroupDto toDtoWithChildren(ProductGroup entity);

    // === List mappings ===
    @IterableMapping(qualifiedByName = "basicToDto")
    List<ProductGroupDto> toDtoList(List<ProductGroup> entities);

    @IterableMapping(qualifiedByName = "basicToEntity")
    List<ProductGroup> toEntityList(List<ProductGroupDto> dtos);
}
