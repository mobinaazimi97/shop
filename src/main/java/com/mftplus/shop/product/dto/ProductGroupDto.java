package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.entity.ProductGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProductGroupDto {

    private Long id;
    private String pGroupName;
    private ProductGroupDto parent;
    private List<ProductGroupDto> childList;
    private GroupPropertyDto groupProperty;

    public ProductGroup getProductGroup() {
        return ProductGroup.builder()
 .id(this.id)
                .pGroupName(this.pGroupName)
                .groupProperty(this.groupProperty != null ? this.groupProperty.getGroupProperty() : null)
                .parent(this.parent != null ? this.parent.getProductGroup() : null)
                .childList(this.childList != null
                        ? this.childList.stream().map(ProductGroupDto::getProductGroup).collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }

public static ProductGroupDto from(ProductGroup entity) {
    if (entity == null) return null;

    return ProductGroupDto.builder()
            .id(entity.getId())
            .pGroupName(entity.getPGroupName())
            .parent(entity.getParent() != null ? from(entity.getParent()) : null)  // تبدیل parent
            .childList(entity.getChildList() != null ?
                    entity.getChildList().stream().map(ProductGroupDto::from).collect(Collectors.toList())
                    : Collections.emptyList())  // تبدیل childList
            .build();
}


    @Override
    public String toString() {
        String parentDetails = (parent != null) ? "Parent(Name: " + parent.getPGroupName() + ", ID: " + parent.getId() + ")" : "No Parent";
        String childDetails = (childList != null && !childList.isEmpty()) ?
                childList.stream().map(child -> "Child(Name: " + child.getPGroupName() + ", ID: " + child.getId() + ")").collect(Collectors.joining(", "))
                : "No Children";

        return "ProductGroupDto(ID: " + id + ", Name: " + pGroupName + ", " + parentDetails + ", " + childDetails + ")";
    }
}
