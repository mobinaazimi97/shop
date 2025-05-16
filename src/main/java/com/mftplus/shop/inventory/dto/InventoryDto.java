package com.mftplus.shop.inventory.dto;

import com.mftplus.shop.InventoryProduct.dto.InventoryProductDto;
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
public class InventoryDto {
    private UUID id;
    private Double quantity;
    private String title;
    private String address;
    private String phone;
    private boolean deleted = false;
    private List<InventoryProductDto> inventoryProducts;

}
