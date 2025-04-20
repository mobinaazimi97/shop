package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.product.dto.ProductDto;
import com.mftplus.shop.product.entity.Product;
import com.mftplus.shop.product.entity.ProductGroup;
import com.mftplus.shop.product.dto.ProductGroupDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = CentralMapperConfig.class)
public interface ProductGroupMapper extends BaseMapper<ProductGroup, ProductGroupDto> {

//    @Named("basicToEntity")
//    @Mapping(target = "parent", ignore = true)  // اگر نمی‌خواهید parent را به طور پیش‌فرض بکشید
//    @Mapping(target = "childList", ignore = true) // اگر نمی‌خواهید childList را به طور پیش‌فرض بکشید
//    ProductGroup toEntity(ProductGroupDto dto);
//
//    @Named("basicToDto")
//    @Mapping(target = "parent", ignore = true)
//    @Mapping(target = "childList", ignore = true)
//    ProductGroupDto toDto(ProductGroup entity);
//
//    // Full mapping with parent and children
//    @Named("toEntityWithParent")
//    @Mapping(target = "parent", qualifiedByName = "basicToEntity")
//    @Mapping(target = "childList", ignore = true)
//    ProductGroup toEntityWithParent(ProductGroupDto dto);
//    @Named("toDtoWithParent")
//    @Mapping(target = "parent", qualifiedByName = "basicToDto")
//    @Mapping(target = "childList", ignore = true)
//    ProductGroupDto toDtoWithParent(ProductGroup entity);
//
//    @IterableMapping(qualifiedByName = "basicToDto")
//    List<ProductGroupDto> toDtoList(List<ProductGroup> entities);



//        // نقشه‌برداری پایه (نادیده گرفتن parent و childList)
//        @Named("basicToEntity")
//        @Mapping(target = "parent", ignore = true)
//        @Mapping(target = "childList", ignore = true)
//        ProductGroup toEntity(ProductGroupDto dto);
//
//        // نقشه‌برداری پایه از موجودیت به DTO (نادیده گرفتن parent و childList)
//        @Named("basicToDto")
//        @Mapping(target = "parent", ignore = true)
//        @Mapping(target = "childList", ignore = true)
//        ProductGroupDto toDto(ProductGroup entity);
//
//        // نقشه‌برداری کامل با والد (بازگشتی برای parent)
//        @Named("toEntityWithParent")
//        @Mapping(target = "parent", qualifiedByName = "basicToEntity")
//        @Mapping(target = "childList", ignore = true)
//        ProductGroup toEntityWithParent(ProductGroupDto dto);
//
//        @Named("toDtoWithParent")
//        @Mapping(target = "parent", qualifiedByName = "basicToDto")
//        @Mapping(target = "childList", ignore = true)
//        ProductGroupDto toDtoWithParent(ProductGroup entity);
//
//    @Named("toDtoWithChildren")
//    @Mapping(target = "parent", ignore = true)
//    @Mapping(target = "childList", qualifiedByName = "toDtoWithChildren")
//    ProductGroupDto toDtoWithChildren(ProductGroup entity);
//
//    // نقشه‌برداری سفارشی برای محدود کردن عمق فرزندان
//    @Named("toDtoWithLimitedChildren")
//    @Mapping(target = "parent", ignore = true)
//    @Mapping(target = "childList", qualifiedByName = "toDtoWithLimitedChildren")
//    ProductGroupDto toDtoWithLimitedChildren(ProductGroup entity);
//
//    // نقشه‌برداری لیست (برای لیست‌های ProductGroup)
//    @IterableMapping(qualifiedByName = "basicToDto")
//    List<ProductGroupDto> toDtoList(List<ProductGroup> entities);
//
//    @IterableMapping(qualifiedByName = "basicToEntity")
//    List<ProductGroup> toEntityList(List<ProductGroupDto> dtos);
//-------------------------------------------------------------------------------------

//    @Named("basicToEntity")
//    @Mapping(target = "parent", ignore = true)
//    @Mapping(target = "childList", ignore = true)
//    ProductGroup toEntity(ProductGroupDto dto);
//
//    @Named("basicToDto")
//    @Mapping(target = "parent", ignore = true)
//    @Mapping(target = "childList", ignore = true)
//    ProductGroupDto toDto(ProductGroup entity);
//
//    // === Full mapping with parent ===
//    @Named("toEntityWithParent")
//    @Mapping(target = "parent", qualifiedByName = "basicToEntity")
//    @Mapping(target = "childList", ignore = true)
//    ProductGroup toEntityWithParent(ProductGroupDto dto);
//
//    @Named("toDtoWithParent")
//    @Mapping(target = "parent", qualifiedByName = "basicToDto")
//    @Mapping(target = "childList", ignore = true)
//    ProductGroupDto toDtoWithParent(ProductGroup entity);
//
//    // === Full mapping with children ===
//    @Named("toDtoWithChildren")
//    @Mapping(target = "parent", ignore = true)
//    @Mapping(target = "childList", qualifiedByName = "toDtoWithChildren")
//    ProductGroupDto toDtoWithChildren(ProductGroup entity);
//
//    // === List mappings ===
//    @IterableMapping(qualifiedByName = "basicToDto")
//    List<ProductGroupDto> toDtoList(List<ProductGroup> entities);
//
//    @IterableMapping(qualifiedByName = "basicToEntity")
//    List<ProductGroup> toEntityList(List<ProductGroupDto> dtos);
}
