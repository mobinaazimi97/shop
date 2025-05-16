package com.mftplus.shop.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mftplus.shop.InventoryProduct.InventoryProduct;
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
@Entity(name = "inventoryEntity")
@Table(name = "inventory_tbl")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 30)
    @JsonProperty("نام")
    private String title;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "quantity")
    private Double quantity;

    @Column(name = "deleted")
    private boolean deleted = false;

    @OneToMany(mappedBy = "inventory")
    private List<InventoryProduct> inventoryProducts = new ArrayList<>();


    //(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH} , fetch = FetchType.EAGER)
//    @ManyToOne
//    @JoinColumn(name = "inventoryProduct_id", foreignKey = @ForeignKey(name="fk_inventory_product"))
//    private InventoryProduct inventoryProduct;


}
