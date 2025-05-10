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
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

    @Query("select p from productGroupEntity p where p.title = :title")
    Optional<ProductGroup> findByTitle(@Param("title") String title);

//    @Query("select p from productGroupEntity p where p.id = :id")
//    Optional<ProductGroup> findByUuid(@Param("uuid") Long uuId);
//    @Query("select p from productGroupEntity p where p.isDeleted=false")
//    List<ProductGroup> findByIsDeletedFalse();


    @Modifying
    @Query("update productGroupEntity p set p.isDeleted=true where p.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);

//    Optional<ProductGroup> findByUuIdAndIsDeletedFalse(Long id);
//
//    List<ProductGroup> findAllByIsDeletedFalse();

    @Query("select p from productGroupEntity p where p.parent.id=:id")
    List<ProductGroup> findByParentId(@Param("id") Long id);
}
