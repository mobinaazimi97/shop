package com.mftplus.shop.role.dto;


import com.mftplus.shop.permission.dto.PermissionDto;
import com.mftplus.shop.permission.entity.Permission;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class RoleDto {
    private UUID id;
    private String roleName;
    private Set<PermissionDto> permissionSet;
}
