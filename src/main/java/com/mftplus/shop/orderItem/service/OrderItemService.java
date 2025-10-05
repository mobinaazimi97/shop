package com.mftplus.shop.orderItem.service;

import com.mftplus.shop.order.dto.OrderItemDto;
import com.mftplus.shop.order.mapper.OrderItemMapper;
import com.mftplus.shop.orderItem.entity.OrderItem;
import com.mftplus.shop.orderItem.repository.OrderItemRepository;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;
    private final UuidMapper uuidMapper;


    public OrderItemService(OrderItemRepository orderItemRepository, OrderItemMapper orderItemMapper, UuidMapper uuidMapper) {
        this.orderItemRepository = orderItemRepository;
        this.orderItemMapper = orderItemMapper;
        this.uuidMapper = uuidMapper;
    }

    @Transactional
    public OrderItemDto save(OrderItemDto dto) {
        OrderItem entity = orderItemMapper.toEntity(dto, "OrderItem");
        OrderItem saved = orderItemRepository.save(entity);
        return orderItemMapper.toDto(saved, "OrderItem");
    }

    @Transactional
    public Optional<OrderItemDto> findById(UUID uuid) {
        Long id = uuidMapper.map(uuid, "OrderItem");
        return orderItemRepository.findById(id)
                .map(orderItem -> orderItemMapper.toDto(orderItem, "OrderItem"));
    }

    @Transactional(readOnly = true)
    public List<OrderItemDto> findAll() {
        return orderItemMapper.toDtoList(orderItemRepository.findAll(), "OrderItem");
    }


    @Transactional
    public OrderItemDto update(UUID uuid, OrderItemDto dto) {
        Long id = uuidMapper.map(uuid, "OrderItem");

        OrderItem existing = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderItem not found"));

        orderItemMapper.updateFromDto(dto, existing, "OrderItem");

        OrderItem updated = orderItemRepository.save(existing);
        return orderItemMapper.toDto(updated, "OrderItem");
    }

    public void deleteOrder(UUID uuid) {
        Long id = uuidMapper.map(uuid, "OrderItem");
        orderItemRepository.logicalRemove(id);
    }
}
