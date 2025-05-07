package com.mftplus.shop.role.service;


import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.repository.PermissionRepository;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.repository.RoleRepository;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.mapper.RoleMapper;
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
public class RoleService{
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;

    @Transactional
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto, "Role");
//        role.setRoleName("ROLE_" + role.getRoleName().toUpperCase());
        Set<Permission> permissions = role.getPermissionSet().stream()
                .map(permission -> permissionRepository.findByPermissionName(permission.getPermissionName())
                        .orElseThrow(() -> new IllegalArgumentException("Permission not found : " + permission.getPermissionName())))
                .collect(Collectors.toSet());
        role.setPermissionSet(permissions);
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole, "Role");
    }

    @Transactional
    public List<RoleDto> saveAll(List<RoleDto> roleDtos) {
        roleDtos.forEach(roleDto -> {
            RoleDto savedRole = save(roleDto);
        });
        return roleDtos;
    }

    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(r -> roleMapper.toDto(r, "Role"))
                .collect(Collectors.toList());
    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return roleMapper.toDto(role, "Role");
    }

    @Transactional
    public RoleDto update(Long id,RoleDto roleDto) {
        Role role = roleRepository.findById(id).get();
        roleMapper.updateFromDto(roleDto, role, "Role");
        return roleMapper.toDto(roleRepository.save(role), "Role");
    }

    @Transactional
    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new IllegalArgumentException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
