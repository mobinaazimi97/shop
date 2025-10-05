package com.mftplus.shop.role.service;


import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.repository.PermissionRepository;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.repository.RoleRepository;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.mapper.RoleMapper;
import com.mftplus.shop.uuid.UuidMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PermissionRepository permissionRepository;
    private final UuidMapper uuidMapper;

    @Transactional
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto, "Role");
        Set<Permission>permissionSet = new HashSet<>();

        if (roleDto.getPermissionSet() != null){
            for (PermissionDto permissionDto : roleDto.getPermissionSet()){
                if (permissionDto.getId() != null){
                    Long id = uuidMapper.map(permissionDto.getId(),"Permission");
                    Permission permission = permissionRepository.findById(id).orElse(null);
                    if (permission != null){
                        permissionSet.add(permission);
                    }
                }else {
                    Permission newPermission = Permission.builder().permissionName(permissionDto.getPermissionName()).build();
                    permissionRepository.save(newPermission);
                    permissionSet.add(newPermission);
                }
            }
            role.setPermissionSet(permissionSet);
        }
        Role savedRole = roleRepository.save(role);
        return roleMapper.toDto(savedRole,"Role");
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
    public RoleDto update(Long id, RoleDto roleDto) {
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
