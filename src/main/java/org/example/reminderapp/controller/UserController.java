package org.example.reminderapp.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserFilterDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.exception.ResourceNotFoundException;
import org.example.reminderapp.service.ReminderService;
import org.example.reminderapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public Page<UserProfileResponseDto> findAll(Pageable pageable) {
        ResponseEntity<Page<UserProfileResponseDto>> users = userService.findAll(pageable);
        return ;
    }

    @GetMapping("/{id}")
    public UserProfileResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserProfileResponseDto create(@Valid @RequestBody UserCreateDto userDto) {
        return userService.create(userDto);
    }

    @PutMapping
    public UserProfileResponseDto update(@PathVariable Long id,
                                         @Valid @RequestBody UserCreateDto userDto) {
        return userService.update(id, userDto);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id) {
        userService.delete(id);
    }
}
