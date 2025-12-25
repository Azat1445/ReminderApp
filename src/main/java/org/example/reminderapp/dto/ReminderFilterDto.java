package org.example.reminderapp.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReminderFilterDto {

    private String username;
    private String title;
    private String description;
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;
}
