package org.example.reminderapp.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReminderCreateDto {

    @NotBlank
    public String title;
    public String description;
    @NotNull
    private OffsetDateTime remindAt;
    @NotNull
    private Long userId;
}
