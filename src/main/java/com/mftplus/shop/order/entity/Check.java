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
@Entity(name = "checkEntity")
@DiscriminatorValue(value = "CHECK")
public class Check extends Payment {

    @Column(name = "checkNum")
    private String checkNumber;

    @Column(name = "bank")
    private String bankName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "check-user",foreignKey = @ForeignKey(name = "fk-users"))
    private User user;

}
