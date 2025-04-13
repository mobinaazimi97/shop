package com.mftplus.shop.order;

import com.mftplus.shop.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
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
public class Card extends Payment{

    @OneToOne(fetch = FetchType.EAGER)
    private User user;
}
