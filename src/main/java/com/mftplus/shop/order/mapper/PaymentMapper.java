package com.mftplus.shop.order.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.order.entity.Payment;
import com.mftplus.shop.order.dto.PaymentDto;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, CheckMapper.class, CardMapper.class})
public abstract class PaymentMapper {

    public abstract PaymentDto toDto(Payment entity, @Context String entityType);

    public abstract Payment toEntity(PaymentDto dto, @Context String entityType);

    public abstract List<PaymentDto> toDtoList(List<? extends Payment> payments, @Context String entityType);

    public abstract List<Payment> toEntityList(List<? extends PaymentDto> paymentDtos, @Context String entityType);

}
