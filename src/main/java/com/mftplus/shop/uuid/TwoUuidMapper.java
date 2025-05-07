package com.mftplus.shop.uuid;

import com.mftplus.shop.config.CentralMapperConfig;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(config = CentralMapperConfig.class)
public abstract class TwoUuidMapper {
    @Autowired
    protected UuidService uuidService;

    @Named("mapIdToUuid")
    public UUID map(Long id, @Context String type) {
        return (id == null) ? null : uuidService.getOrCreateUuid(type, id);
    }

    @Named("mapUuidToId")
    public Long map(UUID uuid, @Context String type) {
        return (uuid == null) ? null : uuidService.getEntityIdByUuid(type, uuid);
    }

}
