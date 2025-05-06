package com.mftplus.shop.uuid;

import com.mftplus.shop.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public abstract class UuidMapper {
    @Autowired
    protected UuidService uuidService;

    public UUID map(Long id) {
        return (id == null) ? null : uuidService.getOrCreateUuid("DEFAULT", id);
    }

    public Long map(UUID uuid) {
        return (uuid == null) ? null : uuidService.getEntityIdByUuid("DEFAULT", uuid);
    }
}
