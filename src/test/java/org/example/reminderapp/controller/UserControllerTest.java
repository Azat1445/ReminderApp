package org.example.reminderapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void createUser() throws Exception {
        UserCreateDto createDto = new UserCreateDto();
        createDto.setUsername("newuser");
        createDto.setEmail("test@example.com");
        createDto.setPassword("password");
        createDto.setFirstname("test");
        createDto.setLastname("test");
        createDto.setBirthDate(LocalDate.of(1990, 1, 1));

        UserProfileResponseDto responseDto = new UserProfileResponseDto();
        responseDto.setId(10L);
        responseDto.setUsername("newuser");

        when(userService.create(any(UserCreateDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.username").value("newuser"));
    }
}

