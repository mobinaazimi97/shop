package com.mftplus.shop.user.service;

import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.service.BaseServiceImpl;
import com.mftplus.shop.user.User;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.mapper.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserSevice extends BaseServiceImpl<User, UserDto, Long>{
    public UserSevice(JpaRepository<User, Long> repository, BaseMapper<User, UserDto> mapper) {
        super(repository, mapper);
    }
}
