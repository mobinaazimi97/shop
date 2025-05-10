package com.mftplus.shop.productGroup.dto;

import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
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
    private GroupPropertyDto groupPropertyDto;
}
