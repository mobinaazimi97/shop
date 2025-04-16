package com.mftplus.shop.product.mapper;

import com.mftplus.shop.product.GroupProperty;
import com.mftplus.shop.product.dto.GroupPropertyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupPropertyMapper {

    GroupPropertyDto toDto(GroupProperty entity);

    GroupProperty toEntity(GroupPropertyDto dto);

    List<GroupPropertyDto> toDtoList(List<GroupProperty> list);
}
