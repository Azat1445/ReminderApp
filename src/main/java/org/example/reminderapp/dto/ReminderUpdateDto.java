package org.example.reminderapp.dto;

import lombok.Data;

import java.time.OffsetDateTime;


@Data
public class ReminderUpdateDto {

    private Long id;
    private String title;
    private String description;
    private OffsetDateTime remindAt;
}
