package com.mftplus.shop.user.controller;

import com.mftplus.shop.user.dto.UserDto;
import com.mftplus.shop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable long id) {
        return userService.findById(id);
    }


    @GetMapping("/username/{username}")
    public UserDto getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }


    @PostMapping
    public UserDto createUser(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userService.delete(id);
    }


}
