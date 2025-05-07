package com.mftplus.shop.productGroup.dto;

import com.mftplus.shop.productGroup.ProductGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class ProductGroupDto {
    private UUID id;
    private String title;
    private UUID parentId;
    private List<UUID> childrenIds = new ArrayList<>();


//    public ProductGroup toEntity(ProductGroup parent) {
//        ProductGroup group = new ProductGroup();
//        group.setId(this.id != null ? this.id : UUID.randomUUID()); // اگر id وجود نداشت خودکار تولید می‌شود
//        group.setTitle(this.title);
//        group.setParent(parent);
//        return group;
//    }

}
