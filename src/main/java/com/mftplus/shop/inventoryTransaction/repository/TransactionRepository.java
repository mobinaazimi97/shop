package com.mftplus.shop.inventoryTransaction.repository;

import com.mftplus.shop.inventoryTransaction.InventoryTransaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<InventoryTransaction, Long> {
    @Modifying
    @Query("update inventoryTransactionEntity i set i.deleted=true where i.id=:id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
