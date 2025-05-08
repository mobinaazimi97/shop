package com.mftplus.shop.productPropertyValue;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PropertyValueRepository extends JpaRepository<PropertyValue, Long> {

    @Modifying
    @Query("update valueEntity v set v.isDeleted=true where v.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
