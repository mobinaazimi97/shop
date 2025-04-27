package com.mftplus.shop;

import com.mftplus.shop.permission.entity.Permission;
import com.mftplus.shop.permission.mapper.PermissionMapper;
import com.mftplus.shop.permission.service.PermissionService;
import com.mftplus.shop.role.entity.Role;
import com.mftplus.shop.role.mapper.RoleMapper;
import com.mftplus.shop.role.service.RoleService;
import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.entity.User;
import com.mftplus.shop.user.mapper.UserMapper;
import com.mftplus.shop.user.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class UserFirstTest implements CommandLineRunner {
    private final PermissionService permissionService;
    private final RoleService roleService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PermissionMapper permissionMapper;
    private final RoleMapper roleMapper;

    public UserFirstTest(PermissionService permissionService, RoleService roleService, UserService userService, UserMapper userMapper, PermissionMapper permissionMapper, RoleMapper roleMapper) {
        this.permissionService = permissionService;
        this.roleService = roleService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.permissionMapper = permissionMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public void run(String... args) {
        Permission permission1 = Permission.builder().permissionName("BUY_PRODUCT").build();
        permissionService.save(permissionMapper.toDto(permission1));

        Permission permission2 = Permission.builder().permissionName("SELL_PRODUCT").build();
        permissionService.save(permissionMapper.toDto(permission2));

        Permission permission3 = Permission.builder().permissionName("EDIT_PRODUCT").build();
        permissionService.save(permissionMapper.toDto(permission3));

        System.out.println("permissions : " + permissionService.findAll());
        System.out.println("------------------------------------------------");

        Role role1 = Role.builder().roleName("ROLE_CUSTOMER").permissionSet(Set.of(permission1, permission2)).build();
        System.out.println(role1);
        System.out.println(roleMapper.toDto(role1));
        roleService.save(roleMapper.toDto(role1));

        Role role2 = Role.builder().roleName("ROLE_USER").permissionSet(Set.of(permission2, permission3)).build();
        roleService.save(roleMapper.toDto(role2));

        System.out.println("roles : " + roleService.findAll());
        System.out.println("------------------------------------------------");

        User user4 = User.builder().username("alex").password("al123").roleSet(Set.of(role1)).accountNonExpired(true).email("www.aaa.com").build();
        User user1 = User.builder().username("sun").password("ss123").roleSet(Set.of(role1)).accountNonExpired(true).email("www.aaa.com").build();
        User user2 = User.builder().username("mahdiar").password("mm123").roleSet(Set.of(role2)).accountNonExpired(true).email("www.mahdiar.com").build();
        User user3 = User.builder().username("mobina").password("m456").roleSet(Set.of(role2)).accountNonExpired(true).email("www.mobina.com").build();
        UserDto userDto1 = userMapper.toDto(user1);
        UserDto userDto2 = userMapper.toDto(user2);
        UserDto userDto3 = userMapper.toDto(user3);
        UserDto userDto4 = userMapper.toDto(user4);
        userService.saveAll(List.of(userDto1, userDto2, userDto3, userDto4));

        System.out.println("users : " + userService.findAll());
        System.out.println("---------------------------------------------------");

        System.out.println("user find by id = 2 :" + userService.findById(2L));
        System.out.println("user find by username = mahdiar :" + userService.findByUsername("mahdiar"));
    }
}
