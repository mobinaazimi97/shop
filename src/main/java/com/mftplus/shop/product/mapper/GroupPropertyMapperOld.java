package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;

import com.mftplus.shop.product.entity.GroupProperty;
import com.mftplus.shop.product.dto.GroupPropertyDto;
import org.mapstruct.Mapper;



@Mapper(config = CentralMapperConfig.class)
//@Component
public interface GroupPropertyMapperOld extends BaseMapper<GroupProperty , GroupPropertyDto> {

//    GroupPropertyDto toDto(GroupProperty entity);
//
//    GroupProperty toEntity(GroupPropertyDto dto);
//
//    List<GroupPropertyDto> toDtoList(List<GroupProperty> list);
}
