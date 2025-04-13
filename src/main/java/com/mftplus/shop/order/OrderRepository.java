package com.mftplus.shop.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM orderEntity o JOIN o.payments p WHERE p.id = :id")
    List<Order> findByPaymentId(@Param("id") Long id);

    @Query("SELECT o FROM orderEntity o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT o FROM orderEntity o WHERE o.amount > :minAmount")
    List<Order> findOrdersByMinAmount(@Param("minAmount") double amount);

    @Query("SELECT o FROM orderEntity o WHERE o.amount < :maxAmount")
    List<Order> findOrdersByMaxAmount(@Param("maxAmount") double amount);

    @Query("SELECT o FROM orderEntity o WHERE o.amount BETWEEN :minAmount AND :maxAmount")
    List<Order> findOrdersByAmountRange(@Param("minAmount") double minAmount, @Param("maxAmount") double maxAmount);


    @Query("SELECT o FROM orderEntity o WHERE o.orderDate > :startDate")
    List<Order> findOrdersAfterDate(@Param("startDate") LocalDate startDate);


    @Query("SELECT o FROM orderEntity o WHERE o.amount BETWEEN :minAmount AND :maxAmount AND o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findOrdersByAmountAndDateRange(
            @Param("minAmount") double minAmount,
            @Param("maxAmount") double maxAmount,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);




}
