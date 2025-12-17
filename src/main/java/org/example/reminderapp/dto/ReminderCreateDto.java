package org.example.reminderapp.dto;


import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReminderCreateDto {

    public String title;
    public String description;
    private OffsetDateTime remindAt;
}
