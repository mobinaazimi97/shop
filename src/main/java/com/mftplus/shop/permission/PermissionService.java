package com.mftplus.shop.permission;

import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.mapper.PermissionMapper;
import com.mftplus.shop.service.BaseServiceImpl;
import com.mftplus.shop.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService extends BaseServiceImpl<Permission, PermissionDto, Long> {

    public PermissionService(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        super(permissionRepository, permissionMapper);
    }
}
