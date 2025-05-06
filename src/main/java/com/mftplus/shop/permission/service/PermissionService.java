package com.mftplus.shop.permission.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.mapper.PermissionMapper;
import com.mftplus.shop.permission.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class PermissionService {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Transactional
    public PermissionDto save(PermissionDto permissionDto) {
        Permission permission = permissionMapper.toEntity(permissionDto);
        return permissionMapper.toDto(permissionRepository.save(permission));
    }

    @Transactional
    public List<PermissionDto> saveAll(List<PermissionDto> permissionDtos) {
        permissionDtos.forEach(permissionDto -> {
            PermissionDto savedPermission = save(permissionDto);
        });
        return permissionDtos;
    }

    public List<PermissionDto> findAll() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toDto)
                .collect(Collectors.toList());
    }

    public PermissionDto findById(Long id) {
        Permission permission = permissionRepository.findById(id).get();
        return permissionMapper.toDto(permission);
    }

    @Transactional
    public PermissionDto update(Long id,PermissionDto permissionDto) {
        Permission permission = permissionRepository.findById(id).get();
        permissionMapper.updateFromDto(permissionDto, permission);
        return permissionMapper.toDto(permissionRepository.save(permission));
    }

    @Transactional
    public void delete(Long id) {
        if (!permissionRepository.existsById(id)) {
            throw new IllegalArgumentException("Permission not found");
        }
        permissionRepository.deleteById(id);
    }
}
