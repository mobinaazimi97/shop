package com.mftplus.shop.user.mapper;

import com.mftplus.shop.config.CentralMapperConfig;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.user.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(config = CentralMapperConfig.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
