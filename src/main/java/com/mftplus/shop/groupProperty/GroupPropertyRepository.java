package com.mftplus.shop.groupProperty;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GroupPropertyRepository extends JpaRepository<GroupProperty, UUID> {

    @Query("select g from groupProEntity g where g.groupName = :groupName")
    Optional<GroupProperty> findByName(@Param("groupName") String groupName);


    @Modifying
    @Query("update groupProEntity g set g.isDeleted=true where g.uuid= :uuid")
    @Transactional
    void logicalRemove(@Param("uuid") UUID uuid);

}
