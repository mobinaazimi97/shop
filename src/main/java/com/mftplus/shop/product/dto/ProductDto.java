package com.mftplus.shop.product.dto;

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
public class ProductDto {
    private UUID id;
    private String productName;
    private Float price;
    private String description;
    private String code;

    @JsonIgnore
    private boolean isDeleted;

    private UUID productGroupId;
    private String productGroupTitle; // عنوان گروه محصول جدید
}
