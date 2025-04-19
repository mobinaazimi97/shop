package com.mftplus.shop.permission;

import com.mftplus.shop.mapper.BaseMapper;
import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.service.BaseServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService extends BaseServiceImpl<Permission, PermissionDto, Long> {
    public PermissionService(JpaRepository<Permission, Long> repository, BaseMapper<Permission, PermissionDto> mapper) {
        super(repository, mapper);
    }
}
