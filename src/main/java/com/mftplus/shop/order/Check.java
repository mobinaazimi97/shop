package com.mftplus.shop.order;

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

@Entity(name = "checkEntity")
@DiscriminatorValue(value = "CHECK")
public class Check extends Payment{

    private String checkNumber;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

}
