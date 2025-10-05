package com.mftplus.shop.user.service;


import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.repository.PermissionRepository;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.repository.RoleRepository;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.user.mapper.UserMapper;
import com.mftplus.shop.user.repository.UserRepository;
import com.mftplus.shop.uuid.UuidMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UuidMapper uuidMapper;
    private final PermissionRepository permissionRepository;

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return userMapper.toDto(user, "User");
    }

    @Transactional
    public UserDto save(UserDto userDto) {
        passwordEncoder.encode(userDto.getPassword());
        User user = userMapper.toEntity(userDto, "User");
        Set<Role> roles = new HashSet<>();

        if (userDto.getRoleSet() != null) {
            for (RoleDto roleDto : userDto.getRoleSet()) {
                Role role;

                if (roleDto.getId() != null) {
                    Long roleId = uuidMapper.map(roleDto.getId(), "Role");
                    role = roleRepository.findById(roleId).orElse(null);
                    if (role == null) continue;
                } else {
                    role = Role.builder().roleName(roleDto.getRoleName()).build();
                }
                Set<Permission> permissions = new HashSet<>();
                if (roleDto.getPermissionSet() != null) {
                    for (PermissionDto permissionDto : roleDto.getPermissionSet()) {
                        Permission permission;
                        if (permissionDto.getId() != null) {
                            Long permId = uuidMapper.map(permissionDto.getId(), "Permission");
                            permission = permissionRepository.findById(permId).orElse(null);
                            if (permission != null) {
                                permissions.add(permission);
                            }
                        } else {
                            permission = Permission.builder().permissionName(permissionDto.getPermissionName()).build();
                            permissionRepository.save(permission);
                            permissions.add(permission);
                        }
                    }
                    role.setPermissionSet(permissions);
                }

                roleRepository.save(role);
                roles.add(role);
            }

            user.setRoleSet(roles);
        }

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
    public UserDto update(Long id, UserDto userDto) {
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
