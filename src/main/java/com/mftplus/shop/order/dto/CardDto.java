package com.mftplus.shop.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class CardDto extends PaymentDto{
    private String bankName;
    private String cardNumber;
    private UUID userId;

}
