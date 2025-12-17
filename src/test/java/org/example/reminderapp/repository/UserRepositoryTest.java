package org.example.reminderapp.repository;

import org.example.reminderapp.entity.User;
import org.junit.jupiter.api.Assertions;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveAndFindUser(){
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@example.com");
        user.setPassword("test");
        user.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));

        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();

        var foundOpt = userRepository.findById(saved.getId());
        assertThat(foundOpt.isPresent());

        User found = foundOpt.get();
        assertThat(found.getUsername()).isEqualTo("test");
        assertThat(found.getEmail()).isEqualTo("test@example.com");
    }
}
