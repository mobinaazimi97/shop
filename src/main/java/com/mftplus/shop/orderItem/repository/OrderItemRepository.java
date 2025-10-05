package com.mftplus.shop.orderItem.repository;

import com.mftplus.shop.orderItem.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    @Modifying
    @Query("update orderItemEntity o set o.deleted=true where o.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
