package org.example.reminderapp.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.mapper.UserMapperDto;
import org.example.reminderapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapperDto userMapperDto;

//    @GetMapping
//    public UserProfileResponseDto findAll() {
//        List<UserProfileResponseDto> usersAll = userService.findAll();
//        return
//    }
//
//    @GetMapping("/{id}")
//    public UserProfileResponseDto findById(@PathVariable Long id) {
//
//    }
//
//    @PostMapping
//    public UserCreateDto createUser(@Valid @RequestBody User user) {
//
//    }
//
//    @PutMapping
//    public UserProfileResponseDto updateUser(@Valid @RequestBody User user) {
//
//    }
//
//    @DeleteMapping
//    public void deleteUser(@RequestParam Long id) {
//        userService.delete(id);
//    }
}
