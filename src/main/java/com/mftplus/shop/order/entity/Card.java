package com.mftplus.shop.order.entity;

import com.mftplus.shop.user.entity.User;
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

@Entity(name = "cardEntity")
@DiscriminatorValue(value = "CARD")
public class Card extends Payment {

    @Column(name = "bank")
    private String bankName;

    @Column(name = "cardNum")
    private String cardNumber;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;
}
