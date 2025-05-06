package com.mftplus.shop.user.dto;

import com.mftplus.shop.role.dto.RoleDto;
import com.mftplus.shop.role.entity.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserDto {
    private UUID id;
    private String username;
    private String password;
    private String email;
    private Set<RoleDto> roleSet;
}
