package org.example.reminderapp.dto;

import lombok.Data;
import org.example.reminderapp.entity.enums.Status;

import java.time.OffsetDateTime;

@Data
public class ReminderResponseDto {

    private Long id;
    private String title;
    private String description;
    private OffsetDateTime remindAt;
    private Status status;

}
