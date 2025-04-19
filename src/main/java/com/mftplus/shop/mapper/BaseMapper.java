package com.mftplus.shop.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapperConfig.class)
public interface BaseMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
    void updateFromDto(D dto,@MappingTarget E entity);
}
