package com.mftplus.shop.orderItem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    public OrderItem update(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }
    public void delete(Long id) {
        orderItemRepository.deleteById(id);
    }
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }
    public OrderItem findById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }
}
