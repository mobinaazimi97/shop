package com.mftplus.shop.product.dto;

import com.mftplus.shop.product.ProductGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductGroupDto {

    private Long id;
    private String name;
    private ProductGroupDto parent;                 // khodesh
    private List<ProductGroupDto> childList;        // khodesh

    public ProductGroup getProductGroup() {
        return ProductGroup.builder()
 .id(this.id)
                .name(this.name)
                .parent(this.parent != null ? this.parent.getProductGroup() : null) // Recursive parent conversion
                .childList(
                        this.childList != null ?
                                this.childList.stream().map(ProductGroupDto::getProductGroup).collect(Collectors.toList())  // Recursive child conversion
                                : Collections.emptyList()
                )
                .build();
    }
    public static ProductGroupDto from(ProductGroup entity) {
        if (entity == null) return null;

        // Convert parent and childList only if not null
        return ProductGroupDto.builder()
                .id(entity.getId()) // Ensure id is properly assigned
                .name(entity.getName())
                .parent(entity.getParent() != null ? from(entity.getParent()) : null)  // Convert parent recursively if present
                .childList(
                        entity.getChildList() != null ?
                                entity.getChildList().stream().map(ProductGroupDto::from).collect(Collectors.toList()) // Convert childList recursively if present
                                : Collections.emptyList()
                )
                .build();

    }
    @Override
    public String toString() {
        String parentDetails = (parent != null) ? "Parent(Name: " + parent.getName() + ", ID: " + parent.getId() + ")" : "No Parent";
        String childDetails = (childList != null && !childList.isEmpty()) ?
                childList.stream().map(child -> "Child(Name: " + child.getName() + ", ID: " + child.getId() + ")").collect(Collectors.joining(", "))
                : "No Children";

        return "ProductGroupDto(ID: " + id + ", Name: " + name + ", " + parentDetails + ", " + childDetails + ")";
    }
}
