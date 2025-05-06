package com.mftplus.shop.productGroup;

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
public interface ProductGroupRepository extends JpaRepository<ProductGroup, UUID> {

    @Query("select p from productGroupEntity p where p.title = :title")
    Optional<ProductGroup> findByTitle(@Param("title") String title);

    @Modifying
    @Query("update productGroupEntity p set p.isDeleted=true where p.uuId= :uuId")
    @Transactional
    void logicalRemove(@Param("uuid") UUID uuId);

    Optional<ProductGroup> findByUuIdAndIsDeletedFalse(UUID uuId);

    List<ProductGroup> findAllByIsDeletedFalse();

    @Query("select p from productGroupEntity p where p.parent.uuId=:uuId")
    List<ProductGroup> findByParentId(@Param("uuid") UUID uuId);
}
