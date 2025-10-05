package com.mftplus.shop.productPropertyValue.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class PropertyValueDto {

    private UUID id;
    private String value;

    @JsonIgnore
    private boolean isDeleted;

}
