package com.mftplus.shop.uuid;

import java.util.UUID;

public interface UuidService {
    UUID getOrCreateUuid(String entityType, Long entityId);
    Long getEntityIdByUuid(String entityType, UUID uuid);
}
