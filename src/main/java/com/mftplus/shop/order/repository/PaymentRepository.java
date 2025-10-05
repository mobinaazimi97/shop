package com.mftplus.shop.order.repository;

import com.mftplus.shop.order.entity.Payment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Modifying
    @Query("update paymentEntity p set p.deleted=true where p.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
