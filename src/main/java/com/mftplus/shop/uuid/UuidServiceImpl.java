package com.mftplus.shop.uuid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UuidServiceImpl implements UuidService {
    @Autowired
    private EntityUuidMapRepository uuidMapRepository;

    @Override
    public UUID getOrCreateUuid(String entityType, Long entityId) {
        return uuidMapRepository.findByEntityTypeAndEntityId(entityType, entityId)
                .map(EntityUuidMap::getUuid)
                .orElseGet(() -> {
                    UUID uuid = UUID.randomUUID();
                    EntityUuidMap map = new EntityUuidMap();
                    map.setEntityId(entityId);
                    map.setEntityType(entityType);
                    map.setUuid(uuid);
                    uuidMapRepository.save(map);
                    return uuid;
                });
    }

    @Override
    public Long getEntityIdByUuid(String entityType, UUID uuid) {
        return uuidMapRepository.findByEntityTypeAndUuid(entityType, uuid)
                .map(EntityUuidMap::getEntityId)
                .orElseThrow(() -> new RuntimeException("UUID not found"));
    }
}
