package com.mftplus.shop.permission;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission update(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission findById(Long permissionId) {
        return permissionRepository.findById(permissionId).orElse(null);
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public void deleteById(Long permissionId) {
        permissionRepository.deleteById(permissionId);
    }



}
