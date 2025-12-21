package org.example.reminderapp.mapper;

import org.example.reminderapp.dto.UserCreateDto;
import org.example.reminderapp.dto.UserProfileResponseDto;
import org.example.reminderapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapperDto userMapper;

    @Test
    void shouldMapUserCreateDtoToEntity() {

        UserCreateDto dto = new UserCreateDto();
        dto.setUsername("testUser");
        dto.setEmail("test@example.com");
        dto.setPassword("password123");
        dto.setFirstname("John");
        dto.setLastname("Doe");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));

        User entity = userMapper.toEntity(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getUsername()).isEqualTo(dto.getUsername());
        assertThat(entity.getEmail()).isEqualTo(dto.getEmail());
        assertThat(entity.getPassword()).isEqualTo(dto.getPassword());

        assertThat(entity.getFirstname()).isEqualTo(dto.getFirstname());
        assertThat(entity.getLastname()).isEqualTo(dto.getLastname());
        assertThat(entity.getBirthDate()).isEqualTo(dto.getBirthDate());
    }

    @Test
    void shouldMapEntityToUserProfileResponseDto() {
        User user = new User();
        user.setId(10L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setFirstname("Jane");
        user.setLastname("Doe");
        user.setBirthDate(LocalDate.of(1995, 5, 5));
        user.setReminders(Collections.emptyList());

        UserProfileResponseDto dto = userMapper.toDto(user);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(user.getId());
        assertThat(dto.getUsername()).isEqualTo(user.getUsername());
        assertThat(dto.getEmail()).isEqualTo(user.getEmail());

        assertThat(dto.getFirstname()).isEqualTo(user.getFirstname());
        assertThat(dto.getLastname()).isEqualTo(user.getLastname());
        assertThat(dto.getBirthDate()).isEqualTo(user.getBirthDate());
    }

}
