package org.example.reminderapp.dto;

import lombok.Value;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Value
public class UserCreateDto {

    private String username;
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate birthDate;
}
