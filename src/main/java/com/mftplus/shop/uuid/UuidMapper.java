package com.mftplus.shop.uuid;

import com.mftplus.shop.config.CentralMapperConfig;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public abstract class UuidMapper {
    @Autowired
    protected UuidService uuidService;

    public UUID map(Long id, @Context String entityType) {
        return (id == null) ? null : uuidService.getOrCreateUuid(entityType, id);
    }

    public Long map(UUID uuid, @Context String entityType) {
        return (uuid == null) ? null : uuidService.getEntityIdByUuid(entityType, uuid);
    }

}
