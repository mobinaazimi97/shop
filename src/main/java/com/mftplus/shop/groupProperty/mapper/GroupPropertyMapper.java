package com.mftplus.shop.groupProperty.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;

import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class})
public interface GroupPropertyMapper {

    GroupPropertyDto toDto(GroupProperty groupProperty, @Context String entityType);

    GroupProperty toEntity(GroupPropertyDto groupPropertyDto, @Context String entityType);

    List<GroupPropertyDto> toDtoList(List<GroupProperty> groupPropertyList, @Context String entityType);

    List<GroupProperty> toEntityList(List<GroupPropertyDto> groupPropertyDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(GroupPropertyDto groupPropertyDto, @MappingTarget GroupProperty groupProperty, @Context String entityType);
}
