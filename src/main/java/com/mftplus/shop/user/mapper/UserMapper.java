package com.mftplus.shop.user.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.permission.mapper.PermissionMapper;
import com.mftplus.shop.role.mapper.RoleMapper;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, RoleMapper.class, PermissionMapper.class})
public interface UserMapper {
    UserDto toDto(User entity);
    User toEntity(UserDto dto);

    List<UserDto> toDtoList(List<User> users);
    List<User> toEntityList(List<UserDto> userDtos);

    void updateFromDto(UserDto dto,@MappingTarget User entity);
}
