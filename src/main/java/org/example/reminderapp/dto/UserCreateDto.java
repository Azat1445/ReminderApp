package org.example.reminderapp.dto;

import lombok.Value;

import java.time.OffsetDateTime;

@Value
public class UserCreateDto {

    private String username;
    private String email;
    private String password;
}
