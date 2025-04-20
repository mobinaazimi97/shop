package com.mftplus.shop.permission.controller;

import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;

    @GetMapping
    public List<PermissionDto> getPermissions() {
        return permissionService.findAll();
    }

    @GetMapping("/{id}")
    public PermissionDto getPermission(@PathVariable long id) {
        return permissionService.findById(id);
    }

    @PostMapping
    public PermissionDto createPermission(@RequestBody @Valid PermissionDto permissionDto) {
        return permissionService.save(permissionDto);
    }

    @PutMapping("/{id}")
    public PermissionDto updatePermission(@PathVariable long id, @RequestBody PermissionDto permissionDto) {
        return permissionService.update(id, permissionDto);
    }

    @DeleteMapping("/{id}")
    public void deletePermission(@PathVariable long id) {
        permissionService.delete(id);
    }

}
