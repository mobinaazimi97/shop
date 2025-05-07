package com.mftplus.shop.groupProperty.dto;

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
    private String groupName;
    private UUID productGroupId;

}
