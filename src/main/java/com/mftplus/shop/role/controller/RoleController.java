package com.mftplus.shop.role.controller;

import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public List<RoleDto> getRoles() {
        return roleService.findAll();
    }

    @GetMapping("/{id}")
    public RoleDto getRole(@PathVariable long id) {
        return roleService.findById(id);
    }

    @PostMapping
    public RoleDto createRole(@RequestBody @Valid RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @PutMapping("/{id}")
    public RoleDto updateRole(@PathVariable long id, @RequestBody RoleDto roleDto) {
        return roleService.update(id, roleDto);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable long id) {
        roleService.delete(id);
    }

}
