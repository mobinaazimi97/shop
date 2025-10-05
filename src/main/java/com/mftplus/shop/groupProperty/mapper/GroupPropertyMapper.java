package com.mftplus.shop.groupProperty.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.groupProperty.GroupProperty;
import com.mftplus.shop.groupProperty.dto.GroupPropertyDto;
import com.mftplus.shop.productPropertyValue.mapper.PropertyValueMapper;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, PropertyValueMapper.class})
public interface GroupPropertyMapper {

    @Mapping(target = "propertyValues", source = "propertyValues")
    GroupPropertyDto toDto(GroupProperty groupProperty, @Context String entityType);

    @Mapping(target = "propertyValues", source = "propertyValues")
    GroupProperty toEntity(GroupPropertyDto groupPropertyDto, @Context String entityType);

    @Mapping(target = "propertyValues", source = "propertyValues")
    List<GroupPropertyDto> toDtoList(List<GroupProperty> groupPropertyList, @Context String entityType);

    @Mapping(target = "propertyValues", source = "propertyValues")
    List<GroupProperty> toEntityList(List<GroupPropertyDto> groupPropertyDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(GroupPropertyDto groupPropertyDto, @MappingTarget GroupProperty groupProperty, @Context String entityType);
}
