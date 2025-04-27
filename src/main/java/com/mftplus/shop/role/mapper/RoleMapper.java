package com.mftplus.shop.role.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.dto.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CentralMapperConfig.class)
public interface RoleMapper extends BaseMapper<Role, RoleDto> {
    @Override
    @Mapping(target = "permissionSet", source = "role.permissionSet")
    RoleDto toDto(Role role);
}
