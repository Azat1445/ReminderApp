package org.example.reminderapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserFilterDto {

    private String firstname;
    private String lastname;
    private LocalDate birthDate;

}
