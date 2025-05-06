package com.mftplus.shop.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from productEntity p where p.isDeleted=false ")
    List<Product> findAll();

    @Query("select p from productEntity p where p.uuid=:uuid and p.isDeleted=false ")
    Optional<Product> findById(UUID uuid);

    @Query("select p from productEntity p where p.productName=:productName ")
    Optional<Product> findByProductName(@Param("productName") String productName);

    @Query("select p from productEntity p where p.productInfos=:productInfos ")
    Optional<Product> findByProductInfo(@Param("productInfos") String productInfo);

    @Modifying
    @Query("update productEntity p set p.isDeleted=true where p.uuid= :uuid")
    @Transactional
    void logicalRemove(@Param("uuid") UUID uuid);
}
