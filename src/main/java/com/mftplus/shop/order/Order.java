package com.mftplus.shop.order;


import com.mftplus.shop.orderItem.OrderItem;
import com.mftplus.shop.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString

@Entity(name = "orderEntity")
@Table(name = "orders_tbl", indexes = {
        @Index(name = "idx_order_number", columnList = "order_number"),
        @Index(name = "idx_order_payments_payment_id", columnList = "payment_id"),
        @Index(name = "idx_order_date", columnList = "order_date"),
        @Index(name = "idx_order_amount", columnList = "order_amount")})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_amount")
    private double amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(
            name = "order_payments",  // جدول واسط
            joinColumns = @JoinColumn(name = "order_id"),   // ارجاع به جدول `invoice`
            inverseJoinColumns = @JoinColumn(name = "payment_id")  // ارجاع به جدول `payment`
    )
    private List<Payment> payments;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "order_orderItem"  // ارجاع به جدول `payment`
    )
    private List<OrderItem>orderItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_info")
    private User user;
}
