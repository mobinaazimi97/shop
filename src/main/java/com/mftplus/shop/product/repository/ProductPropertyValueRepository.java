package com.mftplus.shop.product.repository;

import com.mftplus.shop.product.entity.ProductPropertyValue;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertyValueRepository extends JpaRepository<ProductPropertyValue, Long> {

    @Modifying
    @Query("update productProEntity p set p.isDeleted=true where p.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
