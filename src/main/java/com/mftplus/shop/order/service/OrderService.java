package com.mftplus.shop.order.service;

import com.mftplus.shop.order.entity.Order;
import com.mftplus.shop.order.OrderRepository;
import com.mftplus.shop.order.dto.OrderDto;
import com.mftplus.shop.order.mapper.OrderMapper;
import com.mftplus.shop.uuid.UuidMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final UuidMapper uuidMapper;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;

    public OrderService(UuidMapper uuidMapper, OrderMapper orderMapper, OrderRepository orderRepository) {
        this.uuidMapper = uuidMapper;
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderDto saveOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto, "Order");
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder, "Order");
    }

    @Transactional
    public Optional<OrderDto> findById(UUID uuid) {
        Long id = uuidMapper.map(uuid, "Order");
        return orderRepository.findById(id)
                .map(order -> orderMapper.toDto(order, "Order"));
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDtoList(orders, "Order");
    }

    @Transactional
    public OrderDto updateOrder(UUID uuid, OrderDto orderDto) {
        Long id = uuidMapper.map(uuid, "Order");
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        orderMapper.updateFromDto(orderDto, existingOrder, "Order");

        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDto(updatedOrder, "Order");
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findByDateRange(LocalDate from, LocalDate to) {
        List<Order> orders = orderRepository.findAllByOrderDateBetween(from, to);
        return orderMapper.toDtoList(orders, "Order");
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findByMinAmount(double minAmount) {
        List<Order> orders = orderRepository.findAllByAmountGreaterThanEqual(minAmount);
        return orderMapper.toDtoList(orders, "Order");
    }

    @Transactional(readOnly = true)
    public Page<OrderDto> getPagedOrders(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("orderDate").descending());
        Page<Order> orderPage = orderRepository.findAll(pageable);
        return orderPage.map(order -> orderMapper.toDto(order, "Order"));
    }

    public void deleteOrder(UUID uuid) {
        Long id = uuidMapper.map(uuid, "Order");
        orderRepository.logicalRemove(id);
    }
}
