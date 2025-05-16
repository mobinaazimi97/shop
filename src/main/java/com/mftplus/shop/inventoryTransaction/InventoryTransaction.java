package com.mftplus.shop.inventoryTransaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mftplus.shop.InventoryProduct.InventoryProduct;
import com.mftplus.shop.enums.InventoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "inventoryTransactionEntity")
@Table(name = "Inventory_transactions_tbl")

public class InventoryTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_product_id")
    private InventoryProduct inventoryProduct;

    @Column(name = "count", length = 10)
    private Double count;

    @Enumerated(EnumType.STRING)
    @Column(name = "inventory_status")
    private InventoryStatus status;

    @Column(name = "deleted")
    private boolean deleted=false;

    @Column(name = "transactionAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime transactionDateTime;

}
