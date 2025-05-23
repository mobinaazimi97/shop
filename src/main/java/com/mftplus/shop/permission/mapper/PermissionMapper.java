package com.mftplus.shop.permission.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class})
public interface PermissionMapper {
    PermissionDto toDto(Permission entity, @Context String entityType);
    Permission toEntity(PermissionDto dto, @Context String entityType);
    
    Set<PermissionDto> toDtoSet(Set<Permission> permissions, @Context String entityType);
    Set<Permission> toEntitySet(Set<PermissionDto> permissionDtos, @Context String entityType);

    void updateFromDto(PermissionDto dto, @MappingTarget Permission entity, @Context String entityType);

}
