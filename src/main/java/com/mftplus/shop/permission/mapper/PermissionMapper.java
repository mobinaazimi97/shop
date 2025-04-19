package com.mftplus.shop.permission.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.permission.Permission;
import com.mftplus.shop.permission.dto.PermissionDto;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface PermissionMapper extends BaseMapper<Permission, PermissionDto> {
}
