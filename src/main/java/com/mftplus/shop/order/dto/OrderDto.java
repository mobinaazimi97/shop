package com.mftplus.shop.order.dto;

import com.github.mfathi91.time.PersianDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class OrderDto {


    private UUID id;
    private String orderNumber;
    private LocalDate orderDate;
    private double amount;

    private List<UUID> paymentIds;
    private List<UUID> orderItemIds;
    private UUID userId;

    public String getPersianOrderDate() {
        return PersianDate.fromGregorian(orderDate).toString();
    }

    public void setPersianOrderDate(String persianOrderDate) {
        this.orderDate = PersianDate.parse(persianOrderDate).toGregorian();
    }
}
