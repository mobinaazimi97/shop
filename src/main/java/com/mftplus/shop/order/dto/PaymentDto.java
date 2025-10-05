package com.mftplus.shop.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class PaymentDto {
    private UUID id;
    private double amount;
    private LocalDateTime dateTime;
    private boolean deleted = false;

}
