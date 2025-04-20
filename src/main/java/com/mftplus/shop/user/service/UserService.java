package com.mftplus.shop.user.service;

import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.service.BaseServiceImpl;
import com.mftplus.shop.user.User;
import com.mftplus.shop.user.UserRepository;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImpl<User, UserDto, Long>{
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, userMapper);
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return userMapper.toDto(user);
    }
}
