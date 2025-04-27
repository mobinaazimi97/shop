package com.mftplus.shop.user.dto;

import com.mftplus.shop.role.entity.Role;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@ToString
public class UserDto {
    private String username;
    private String password;
    private String email;
    private Set<Role> roleSet;
}
