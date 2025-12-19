package org.example.reminderapp.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ReminderFilterDto {

    private String title;
    private String description;
    private OffsetDateTime fromDate;
    private OffsetDateTime toDate;
    private Long userId;
}
