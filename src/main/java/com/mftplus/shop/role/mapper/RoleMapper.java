package com.mftplus.shop.role.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.permission.mapper.PermissionMapper;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, PermissionMapper.class})
public interface RoleMapper  {
    RoleDto toDto(Role entity, @Context String entityType);
    Role toEntity(RoleDto dto, @Context String entityType);

    Set<RoleDto> toDtoSet(Set<Role> roles, @Context String entityType);
    Set<Role> toEntitySet(Set<RoleDto> roleDtos, @Context String entityType);

    void updateFromDto(RoleDto roleDto,@MappingTarget Role role, @Context String entityType);
}
