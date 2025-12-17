package org.example.reminderapp.repository;

import org.example.reminderapp.entity.Reminder;
import org.example.reminderapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReminderRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReminderRepository reminderRepository;

    @Test
    void saveReminderWithUser() {
        User user = new User();
        user.setUsername("rem-user");
        user.setEmail("rem@example.com");
        user.setPassword("pass");
        user.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
        User savedUser = userRepository.save(user);

        Reminder reminder = new Reminder();
        reminder.setTitle("Test title");
        reminder.setDescription("Desc");
        reminder.setRemindAt(OffsetDateTime.now(ZoneOffset.UTC).plusMinutes(5));
        reminder.setUser(savedUser);

        Reminder saved = reminderRepository.save(reminder);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUser()).isNotNull();
        assertThat(saved.getUser().getId()).isEqualTo(savedUser.getId());
    }
}
