package com.mftplus.shop.user.service;


import com.mftplus.shop.user.User;
import com.mftplus.shop.user.UserRepository;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.mapper.UserMapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService_old {
    @Autowired
    private UserMapper userMapper;

    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User Not Found!"));
        return userMapper.toDto(user);
    }

    public UserDto save(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new EntityExistsException("User already exists!");
        }
        User user = userMapper.toEntity(userDto);
//      todo: encrypt password before save
//      todo: set other User fields at first like enable
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto update(long id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User Not Found!"));
        userMapper.updateFromDto(userDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    public void delete(long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User Not Found!");
        }
        userRepository.deleteById(id);
    }

}