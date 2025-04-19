package com.mftplus.shop.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;


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
}
