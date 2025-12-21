package org.example.reminderapp.service;


import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.mapper.UserMapperDto;
import org.example.reminderapp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapperDto userMapperDto;

    @InjectMocks
    private UserService userService;


    @Test
    void createUser() {
        UserCreateDto createDto = new UserCreateDto();
        createDto.setUsername("testuser");

        User userEntity = new User();
        userEntity.setUsername("testuser");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");

        UserProfileResponseDto responseDto = new UserProfileResponseDto();
        responseDto.setId(1L);
        responseDto.setUsername("testuser");

        when(userMapperDto.toEntity(createDto)).thenReturn(userEntity);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userMapperDto.toDto(savedUser)).thenReturn(responseDto);

        UserProfileResponseDto result = userService.create(createDto);

        assertEquals(1L, result.getId());
        assertEquals("testuser", result.getUsername());

        verify(userRepository).save(any(User.class));
    }
}
