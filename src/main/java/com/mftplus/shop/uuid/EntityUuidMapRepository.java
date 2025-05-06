package com.mftplus.shop.uuid;

import com.mftplus.shop.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EntityUuidMapRepository extends JpaRepository<EntityUuidMap, Long> {
    Optional<EntityUuidMap> findByEntityTypeAndEntityId(String entityType, Long entityId);
    Optional<EntityUuidMap> findByEntityTypeAndUuid(String entityType, UUID uuid);
}

