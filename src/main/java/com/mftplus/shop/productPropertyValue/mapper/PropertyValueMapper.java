package com.mftplus.shop.productPropertyValue.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.productPropertyValue.PropertyValue;
import com.mftplus.shop.productPropertyValue.dto.PropertyValueDto;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;


@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class})
public interface PropertyValueMapper {
    PropertyValueDto toDto(PropertyValue propertyValue, @Context String entityType);

    PropertyValue toEntity(PropertyValueDto propertyValueDto, @Context String entityType);

    List<PropertyValueDto> toDtoList(List<PropertyValue> propertyValueList, @Context String entityType);

    List<PropertyValue> toEntityList(List<PropertyValueDto> propertyValueDtoList, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(PropertyValueDto propertyValueDto, @MappingTarget PropertyValue propertyValue, @Context String entityType);

}
