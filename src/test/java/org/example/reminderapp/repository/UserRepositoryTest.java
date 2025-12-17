package org.example.reminderapp.repository;

import org.example.reminderapp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

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

        user.setFirstname("Ivan");
        user.setLastname("Ivanov");
        user.setBirthDate(LocalDate.of(2000,10,5));

        User saved = userRepository.save(user);

        assertThat(saved.getId()).isNotNull();

        Optional<User> found = userRepository.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("test");
        assertThat(found.get().getEmail()).isEqualTo("test@example.com");
        assertThat(found.get().getBirthDate()).isEqualTo(LocalDate.of(2000,10,5));
    }
}
