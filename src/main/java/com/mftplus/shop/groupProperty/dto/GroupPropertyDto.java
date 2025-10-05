package com.mftplus.shop.groupProperty.dto;

import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class GroupPropertyDto {

    private UUID id;
    private String groupName;
    private List<PropertyValueDto> propertyValues;
}
