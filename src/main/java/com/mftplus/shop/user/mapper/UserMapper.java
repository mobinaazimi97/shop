package com.mftplus.shop.user.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.user.User;
import com.mftplus.shop.user.dto.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = CentralMapperConfig.class)
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDto userDto);

    void updateUserFromUserDto(UserDto userDto, @MappingTarget User user);
}
