package org.example.reminderapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserFilterDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;


    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UserProfileResponseDto> findAll(UserFilterDto filter, Pageable pageable) {
        log.info("Request to find all users with filter {}", filter);
        return userService.findAll(filter, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfileResponseDto findById(@PathVariable Long id) {
        log.info("Request to find user by id {}", id);
        return userService.findById(id);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserProfileResponseDto create(@Valid @RequestBody UserCreateDto userDto) {
        log.info("Request  to create user {}", userDto.getUsername());
        return userService.create(userDto);
    }

    @PutMapping("/{id}")
    public UserProfileResponseDto update(@PathVariable Long id,
                                         @Valid @RequestBody UserCreateDto userDto) {
        log.info("Request to update user id: {}", id);
        return userService.update(id, userDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Request to delete user id: {}", id);
        userService.delete(id);
    }
}
