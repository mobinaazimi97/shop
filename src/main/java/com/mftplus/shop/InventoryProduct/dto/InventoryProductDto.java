package com.mftplus.shop.InventoryProduct.dto;

import com.mftplus.shop.inventoryTransaction.dto.TransactionDto;
import jakarta.persistence.Column;
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
public class InventoryProductDto {

    private UUID id;
    private Double quantity;
    private String title;
    private String address;
    private String phone;
    private boolean deleted = false;


    private UUID productId;
    private String productName;

    private UUID inventoryId;
    private String inventoryName;
    private String inventoryPhone;
    private String inventoryAddress;
    private Double inventoryQuantity;

    private List<TransactionDto> transactions;


}
