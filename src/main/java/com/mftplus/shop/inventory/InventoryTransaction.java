package com.mftplus.shop.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mftplus.shop.enums.InventoryStatus;
import com.mftplus.shop.orderItem.OrderItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "inventoryTransactionEntity")
@Table(name = "Inventory_transactions_tbl")

public class InventoryTransaction {

    @Id
    @SequenceGenerator(name = "inventory_t_seq", sequenceName = "inventory_t_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_t_seq")
    @JsonProperty("ردیف")
    private Long id;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "inventory_product_transaction")
    private List<InventoryProduct> inventoryProducts;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = ("order_item_transaction_inventory"))
    @JsonProperty("فاکتور")
    private OrderItem orderItem;

    @Column(name = "count", length = 10)
    @JsonProperty("تعداد")
    private Double count;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "inventory_status")
    @JsonProperty("وضعیت")
    private InventoryStatus status;

}
