package com.mftplus.shop.inventoryTransaction.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mftplus.shop.enums.InventoryStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class TransactionDto {

    private UUID id;
    private Double count;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private InventoryStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDateTime;

    private boolean deleted=false;

    private UUID inventoryProductId;
    private String inventoryProductName;
    private String inventoryProductPhone;
    private String inventoryProductAddress;
}
