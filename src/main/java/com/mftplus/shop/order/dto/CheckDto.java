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
public class CheckDto extends PaymentDto {
    private String checkNumber;
    private String bankName;
    private UUID userId;
}
