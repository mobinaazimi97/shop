package com.mftplus.shop.product.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.product.dto.GroupPropertyDto;
import com.mftplus.shop.product.entity.GroupProperty;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface GroupPropertyMapper extends BaseMapper<GroupProperty, GroupPropertyDto> {
}
