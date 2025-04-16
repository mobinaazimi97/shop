package com.mftplus.shop.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mftplus.shop.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "inventory_product")
@Table(name = "inventoryProduct_tbl")
public class InventoryProduct {

    @Id
    @SequenceGenerator(name = "inventory_product_seq", sequenceName = "inventory_product_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_product_seq")
    @JsonProperty("ردیف")
    private Long id;

    @Column(name = "quantity")
    @JsonProperty("تعداد کالا")
    private Double quantity;

    @Column(name = "title", length = 30)
    @JsonProperty("نام")
    private String title;

    @Column(name = "address", length = 200)
    @JsonProperty("آدرس")
    private String address;

    @Column(name = "phone", length = 13)
    @JsonProperty("شماره تماس")
    private String phone;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "product_in_inventory", foreignKey = @ForeignKey(name = "fk-product"))
    @JsonProperty("کالا")
    private Product products;

}
