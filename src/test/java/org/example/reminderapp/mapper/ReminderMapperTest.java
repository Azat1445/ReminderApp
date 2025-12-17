package org.example.reminderapp.mapper;

import org.example.reminderapp.dto.ReminderCreateDto;
import org.example.reminderapp.entity.Reminder;
import org.example.reminderapp.entity.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReminderMapperTest {

    @Autowired
    private ReminderMapperDto mapper;

    @Test
    void shouldMapCreateDtoToEntity() {
        ReminderCreateDto dto = new ReminderCreateDto();
        dto.setTitle("Spring");
        dto.setDescription("Spring is very hard learning...");
        dto.setRemindAt(OffsetDateTime.now());

        Reminder entity = mapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getTitle()).isEqualTo("Spring");
        assertThat(entity.getDescription()).isEqualTo("Spring is very hard learning...");
        assertThat(entity.getStatus()).isEqualTo(Status.PENDING);
        assertThat(entity.getId()).isNull();

    }
}
