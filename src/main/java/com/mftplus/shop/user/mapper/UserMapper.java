package com.mftplus.shop.user.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.role.repository.RoleRepository;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.uuid.UuidMapper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = CentralMapperConfig.class, uses = {UuidMapper.class, RoleRepository.class})
public interface UserMapper {
    @Mapping(target = "roleSet", source = "roleSet")
    UserDto toDto(User entity, @Context String entityType);

    User toEntity(UserDto dto, @Context String entityType);

    @Mapping(target = "roleSet", source = "roleSet")
    List<UserDto> toDtoList(List<User> users, @Context String entityType);

    List<User> toEntityList(List<UserDto> userDtos, @Context String entityType);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(UserDto dto, @MappingTarget User entity, @Context String entityType);
}
