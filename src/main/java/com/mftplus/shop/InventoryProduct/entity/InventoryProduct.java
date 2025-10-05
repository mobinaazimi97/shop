package com.mftplus.shop.InventoryProduct.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mftplus.shop.inventory.entity.Inventory;
import com.mftplus.shop.inventoryTransaction.entity.InventoryTransaction;
import com.mftplus.shop.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "inventoryProductEntity")
@Table(name = "inventoryProduct_tbl")
public class InventoryProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "deleted")
    private boolean deleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @OneToMany(mappedBy = "inventoryProduct", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"inventoryProduct"})
    private List<InventoryTransaction> transactions = new ArrayList<>();
}
