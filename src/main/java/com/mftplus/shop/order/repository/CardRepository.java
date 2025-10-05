package com.mftplus.shop.order.repository;

import com.mftplus.shop.order.entity.Card;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Modifying
    @Query("update cardEntity c set c.deleted=true where c.id=:id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
