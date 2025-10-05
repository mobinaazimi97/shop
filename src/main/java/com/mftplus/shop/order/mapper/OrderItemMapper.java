package com.mftplus.shop.order.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.order.dto.OrderItemDto;
import com.mftplus.shop.orderItem.entity.OrderItem;
import com.mftplus.shop.product.mapper.ProductMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, ProductMapper.class})
public interface OrderItemMapper {

    @Mapping(target = "productId", source = "product.id")
    OrderItemDto toDto(OrderItem orderItem, @Context String entityType);

    OrderItem toEntity(OrderItemDto orderItemDto, @Context String entityType);

    List<OrderItemDto> toDtoList(List<OrderItem> orderItemList, @Context String entityType);

    List<OrderItem> toEntityList(List<OrderItemDto> orderItemDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(OrderItemDto orderItemDto, @MappingTarget OrderItem orderItem, @Context String entityType);
}
