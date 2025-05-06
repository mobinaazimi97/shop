package com.mftplus.shop.orderItem;


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

@Entity(name = "OrderItemEntity")
@Table(name = "order_item_tbl")
public class OrderItem {
    @Id
    @SequenceGenerator(name = "orderItemSeq", sequenceName = "order_item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderItemSeq")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "item_product_TBL")
    private Product product;

    @Transient
    private double amount;
    private int quantity;
    private double price;

    public double getAmount() {
        amount = quantity * price;
        return amount;
    }
}
