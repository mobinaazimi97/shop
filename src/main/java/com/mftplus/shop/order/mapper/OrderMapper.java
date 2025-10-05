package com.mftplus.shop.order.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.order.entity.Order;
import com.mftplus.shop.order.entity.Payment;
import com.mftplus.shop.order.dto.OrderDto;
import com.mftplus.shop.order.repository.PaymentRepository;
import com.mftplus.shop.orderItem.entity.OrderItem;
import com.mftplus.shop.orderItem.repository.OrderItemRepository;
import com.mftplus.shop.user.mapper.UserMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(
        config = CentralMapperConfig.class,
        uses = {
                UuidMapper.class,
                PaymentMapper.class,
                OrderItemMapper.class,
                UserMapper.class
        }
)
public abstract class OrderMapper {

    @Autowired
    protected UuidMapper uuidMapper;

    @Autowired
    protected PaymentRepository paymentRepository;

    @Autowired
    protected OrderItemRepository orderItemRepository;

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "paymentIds", source = "payments", qualifiedByName = "mapPaymentsToIds")
    @Mapping(target = "orderItemIds", source = "orderItems", qualifiedByName = "mapOrderItemsToIds")
    public abstract OrderDto toDto(Order order, @Context String entityType);

    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "payments", source = "paymentIds", qualifiedByName = "mapIdsToPayments")
    @Mapping(target = "orderItems", source = "orderItemIds", qualifiedByName = "mapIdsToOrderItems")
    public abstract Order toEntity(OrderDto dto, @Context String entityType);

    public abstract List<OrderDto> toDtoList(List<Order> orders, @Context String entityType);

    public abstract List<Order> toEntityList(List<OrderDto> orderDtos, @Context String entityType);

    public abstract void updateFromDto(OrderDto dto, @MappingTarget Order entity, @Context String entityType);

    @Named("mapPaymentsToIds")
    protected List<UUID> mapPaymentsToIds(List<Payment> payments, @Context String entityType) {
        if (payments == null) return null;
        return payments.stream()
                .map(p -> uuidMapper.map(p.getId(), entityType))
                .collect(Collectors.toList());
    }

    @Named("mapIdsToPayments")
    protected List<Payment> mapIdsToPayments(List<UUID> uuids, @Context String entityType) {
        if (uuids == null) return null;
        return uuids.stream()
                .map(uuid -> paymentRepository.findById(uuidMapper.map(uuid, entityType))
                        .orElseThrow(() -> new RuntimeException("Payment not found: " + uuid)))
                .collect(Collectors.toList());
    }

    @Named("mapOrderItemsToIds")
    protected List<UUID> mapOrderItemsToIds(List<OrderItem> items, @Context String entityType) {
        if (items == null) return null;
        return items.stream()
                .map(i -> uuidMapper.map(i.getId(), entityType))
                .collect(Collectors.toList());
    }

    @Named("mapIdsToOrderItems")
    protected List<OrderItem> mapIdsToOrderItems(List<UUID> uuids, @Context String entityType) {
        if (uuids == null) return null;
        return uuids.stream()
                .map(uuid -> orderItemRepository.findById(uuidMapper.map(uuid, entityType))
                        .orElseThrow(() -> new RuntimeException("OrderItem not found: " + uuid)))
                .collect(Collectors.toList());
    }
}
