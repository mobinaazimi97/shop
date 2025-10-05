package com.mftplus.shop.order;

import com.mftplus.shop.order.entity.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Order order) {
        return orderRepository.save(order);
    }

    public void removeById(Long id) {
        orderRepository.deleteById(id);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findByPaymentId(Long id) {
        return orderRepository.findByPaymentId(id);
    }

    public List<Order> findOrdersInDateRange(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findOrdersInDateRange(startDate, endDate);
    }

    public List<Order> findOrdersByMinAmount(double amount) {
        return orderRepository.findOrdersByMinAmount(amount);
    }

    public List<Order> findOrdersByMaxAmount(double amount) {
        return orderRepository.findOrdersByMaxAmount(amount);
    }

    public List<Order> findOrdersAfterDate(LocalDate startDate) {
        return orderRepository.findOrdersAfterDate(startDate);

    }

    public List<Order> findOrdersByAmountRange(double minAmount, double maxAmount) {
        return orderRepository.findOrdersByAmountRange(minAmount, maxAmount);

    }

    public List<Order> findOrdersByAmountAndDateRange(double minAmount, double maxAmount, LocalDate startDate, LocalDate endDate) {
        return orderRepository.findOrdersByAmountAndDateRange(minAmount, maxAmount, startDate, endDate);
    }


}
