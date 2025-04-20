package com.mftplus.shop.permission.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.mapper.PermissionMapper;
import com.mftplus.shop.permission.repository.PermissionRepository;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
//@CacheableLevel(cacheName = "permission_name")
//@CacheEvictLevel(cacheNames = "permission_name")
public class PermissionService extends BaseServiceImpl<Permission, PermissionDto, Long> {
    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    public PermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper, PermissionRepository permissionRepository1, PermissionMapper permissionMapper1) {
        super(permissionRepository, permissionMapper);
        this.permissionRepository = permissionRepository1;
        this.permissionMapper = permissionMapper1;
    }
}
