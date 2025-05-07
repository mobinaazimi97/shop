package com.mftplus.shop.user.service;


import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.repository.RoleRepository;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.user.repository.UserRepository;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return userMapper.toDto(user, "User");
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto, "User");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = user.getRoleSet().stream()
                .map(role -> roleRepository.findByRoleName(role.getRoleName())
                        .orElseThrow(() -> new IllegalArgumentException("Role not found : " + role.getRoleName())))
                .collect(Collectors.toSet());
        user.setRoleSet(roles);
        user.setCredentialsExpiryDate(LocalDateTime.now().plusMonths(6));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser, "User");
    }

    @Transactional
    public List<UserDto> saveAll(List<UserDto> userDtos) {
        userDtos.forEach(userDto -> {
            UserDto savedUser = save(userDto);
        });
        return userDtos;
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(u -> userMapper.toDto(u, "User"))
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id).get();
        return userMapper.toDto(user, "User");
    }

    @Transactional
    public UserDto update(Long id,UserDto userDto) {
        User user = userRepository.findById(id).get();
        userMapper.updateFromDto(userDto, user, "User");
        return userMapper.toDto(userRepository.save(user), "User");
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }
}
