package com.mftplus.shop.user.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.service.BaseServiceImpl;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.user.repository.UserRepository;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
//@CacheableLevel(cacheName = "user_cache")
//@CacheEvictLevel(cacheNames = "user_cache")
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
