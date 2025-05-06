package com.mftplus.shop.groupProperty.dto;

import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.productGroup.ProductGroup;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class GroupPropertyDto {

    private UUID id;
    private String name;
    private String productGroupTitle;
    private UUID productGroupId;

    public GroupProperty toEntity(ProductGroup productGroup) {
        GroupProperty groupProperty = new GroupProperty();
        groupProperty.setUuid(this.id != null ? this.id : UUID.randomUUID());
        groupProperty.setGroupName(this.name);

        if (this.productGroupId != null && productGroup != null) {
            groupProperty.setProductGroup(productGroup); // ست کردن parent از بیرون
        }

        return groupProperty;
    }

}
