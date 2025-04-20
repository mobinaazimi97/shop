package com.mftplus.shop.role.service;

import com.mftplus.shop.config.CacheEvictLevel;
import com.mftplus.shop.config.CacheableLevel;
import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.mapper.RoleMapper;
import com.mftplus.shop.role.repository.RoleRepository;
import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
//@CacheableLevel(cacheName = "role_cache")
//@CacheEvictLevel(cacheNames = "role_cache")
public class RoleService extends BaseServiceImpl<Role, RoleDto, Long>{
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        super(roleRepository, roleMapper);
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }
}