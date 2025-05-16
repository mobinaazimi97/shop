package com.mftplus.shop.InventoryProduct.repository;

import com.mftplus.shop.InventoryProduct.InventoryProduct;
import com.mftplus.shop.inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InventoryProductRepository extends JpaRepository<InventoryProduct, Long> {
    List<InventoryProduct> findByInventory(Inventory inventory);

    @Modifying
    @Transactional
    @Query("update inventoryProductEntity i set i.deleted=true where i.id=:id")
    void logicalRemove(@Param("id") Long id);


}
