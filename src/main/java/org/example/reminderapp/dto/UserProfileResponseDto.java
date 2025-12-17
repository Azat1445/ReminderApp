package org.example.reminderapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserProfileResponseDto {

    private Long id;
    private String username;
    private String email;
    private List<ReminderResponseDto> reminders;

}
