package com.mftplus.shop.productGroup.mapper;

import com.mftplus.shop.productGroup.ProductGroup;
import com.mftplus.shop.productGroup.dto.ProductGroupDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface ProductGroupMapper {

    @Mapping(source = "uuId", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "parent.uuId", target = "parentId") // درست است، فقط مطمئن شوید که parent موجود باشد.
    @Mapping(target = "childrenIds", expression = "java(mapChildrenIds(productGroup.getChildList()))")
    ProductGroupDto toDto(ProductGroup productGroup);

    @InheritInverseConfiguration
    @Mapping(target = "childList", ignore = true)
    @Mapping(target = "parent", ignore = true) // نادیده گرفتن parent چون در DTO شناخته نمی‌شود.
    @Mapping(target = "isDeleted", ignore = true)
        // اگر این فیلد وجود داشته باشد باید آن را هم نادیده بگیرید.
    ProductGroup toEntity(ProductGroupDto dto);

    // متد کمکی برای تبدیل لیست childList به لیست UUID
    default List<UUID> mapChildrenIds(List<ProductGroup> children) {
        if (children == null) return new ArrayList<>();
        return children.stream()
                .map(ProductGroup::getUuId)  // تبدیل لیست childList به لیست UUID
                .collect(Collectors.toList());
    }
}
