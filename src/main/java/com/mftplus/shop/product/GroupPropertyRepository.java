package com.mftplus.shop.product;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupPropertyRepository extends JpaRepository<GroupProperty,Long> {

    @Modifying
    @Query("update groupProEntity g set g.isDeleted=true where g.id= :id")
    @Transactional
    void logicalRemove(@Param("id") Long id);
}
