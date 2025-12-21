package org.example.reminderapp.service;


import org.example.reminderapp.dto.ReminderCreateDto;
import org.example.reminderapp.dto.ReminderFilterDto;
import org.example.reminderapp.dto.ReminderResponseDto;
import org.example.reminderapp.entity.Reminder;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.entity.enums.Status;
import org.example.reminderapp.mapper.ReminderMapperDto;
import org.example.reminderapp.repository.ReminderRepository;
import org.example.reminderapp.repository.UserRepository;
import org.example.reminderapp.repository.specification.ReminderSpecification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReminderServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ReminderRepository reminderRepository;

    @Mock
    private ReminderMapperDto reminderMapperDto;

    @InjectMocks
    private ReminderService reminderService;

    @Test
    void createReminder() {
        ReminderCreateDto dto = new ReminderCreateDto();
        dto.setTitle("test");
        dto.setDescription("test");
        dto.setRemindAt(OffsetDateTime.now());
        dto.setUserId(1L);

        User user = new User();
        user.setId(1L);

        Reminder reminderEntity = new Reminder();
        reminderEntity.setTitle("test");

        Reminder savedReminder = new Reminder();
        savedReminder.setId(10L);
        savedReminder.setTitle("test");
        savedReminder.setStatus(Status.PENDING);
        savedReminder.setUser(user);

        ReminderResponseDto responseDto = new ReminderResponseDto();
        responseDto.setId(10L);
        responseDto.setTitle("test");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(reminderMapperDto.toEntity(dto)).thenReturn(reminderEntity);
        when(reminderRepository.save(any(Reminder.class))).thenReturn(savedReminder);
        when(reminderMapperDto.toDto(savedReminder)).thenReturn(responseDto);

        ReminderResponseDto result = reminderService.create(dto);

        assertThat(result.getId()).isEqualTo(10L);
        assertThat(result.getTitle()).isEqualTo("test");

        verify(userRepository).findById(1L);
        verify(reminderRepository).save(any(Reminder.class));
    }

    @Test
    void findAll(){
        ReminderFilterDto filter = new ReminderFilterDto();
        filter.setTitle("test");
        PageRequest pageable = PageRequest.of(0, 10);

        Reminder reminder = new Reminder();
        reminder.setId(1L);
        reminder.setTitle("test");

        ReminderResponseDto reminderResponseDto = new ReminderResponseDto();
        reminderResponseDto.setId(1L);
        reminderResponseDto.setTitle("test");

        Page<Reminder> page = new PageImpl<>(List.of(reminder), pageable, 1);

        when(reminderRepository.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(page);
        when(reminderMapperDto.toDto(reminder))
                .thenReturn(reminderResponseDto);

        Page<ReminderResponseDto> result = reminderService.findAll(filter, pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).getId()).isEqualTo(1);

        verify(reminderRepository).findAll(any(Specification.class), eq(pageable));
    }
}
