package org.example.reminderapp.config;

import lombok.RequiredArgsConstructor;
import org.example.reminderapp.entity.Reminder;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.repository.ReminderRepository;
import org.example.reminderapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

//@Component
@RequiredArgsConstructor
public class DemoDataRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ReminderRepository reminderRepository;

    @Override
    public void run(String... args) throws Exception {
//        User user = new User();
//        user.setUsername("test");
//        user.setEmail("test@example.com");
//        user.setPassword("test");
//        user.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
//
//        User savedUser = userRepository.save(user);
//
//        Reminder reminder = new Reminder();
//        reminder.setTitle("Spring");
//        reminder.setDescription("Spring Reminder this my first project");
//        reminder.setRemindAt(OffsetDateTime.now(ZoneOffset.UTC).plusMinutes(10));
//        reminder.setUser(savedUser);
//
//        reminderRepository.save(reminder);
    }


}
