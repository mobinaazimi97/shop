package com.mftplus.shop.product.repository;

import com.mftplus.shop.product.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Query("update productEntity p set p.isDeleted=true where p.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);

    @Query("select p from productEntity p where p.name like:name")
    Product findByName(@Param("name") String name);

    @Query("select p from productEntity p join fetch p.productGroup where p.productGroup.id = :id")
    Product findByProductGroupId(@Param("id") Long id);

//    @Query("SELECT p FROM productEntity p WHERE LOWER(p.productGroup.name) LIKE LOWER(CONCAT('%', :groupName, '%'))")
    @Query("select p from productEntity p join fetch p.productGroup where p.productGroup.pGroupName = :pGroupName")
    Product findByProductGroupName(@Param("pGroupName") String pGroupName);

//    @Query("SELECT p FROM productEntity p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
//            "AND LOWER(p.productGroup.name) LIKE LOWER(CONCAT('%', :name, '%'))")

@Query("select p from productEntity p join fetch p.productGroup where p.productGroup.pGroupName = :pGroupName and p.name = :name")
Product findByProductNameAndProductGroupName(@Param("pGroupName") String pGroupName,@Param("name") String name);
}
