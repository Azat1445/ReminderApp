package org.example.reminderapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reminderapp.dto.*;
import org.example.reminderapp.entity.Reminder;
import org.example.reminderapp.entity.User;
import org.example.reminderapp.entity.enums.Status;
import org.example.reminderapp.exception.ResourceNotFoundException;
import org.example.reminderapp.mapper.ReminderMapperDto;
import org.example.reminderapp.repository.ReminderRepository;
import org.example.reminderapp.repository.UserRepository;
import org.example.reminderapp.repository.specification.ReminderSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReminderService {

    private final UserRepository userRepository;
    private final ReminderRepository reminderRepository;
    private final ReminderMapperDto reminderMapperDto;

    @Transactional
    public Page<ReminderResponseDto> findAll(ReminderFilterDto filter, Pageable pageable) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        filter.setUsername(username);

        Specification<Reminder> specification = ReminderSpecification.withFilters(filter);

        return reminderRepository.findAll(specification, pageable)
                .map(reminderMapperDto::toDto);
    }

    public ReminderResponseDto findById(Long id) {
        return reminderRepository.findById(id)
                .map(reminderMapperDto::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public ReminderResponseDto create(ReminderCreateDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Reminder reminder = reminderMapperDto.toEntity(dto);

        reminder.setUser(user);
        reminder.setStatus(Status.PENDING);

        Reminder savedEntity = reminderRepository.save(reminder);
        return reminderMapperDto.toDto(savedEntity);
    }

    @Transactional
    public ReminderResponseDto update(Long id, ReminderUpdateDto dto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found"));

        if (!reminder.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("Access denied");
        }

        reminderMapperDto.updateEntity(dto, reminder);

        Reminder savedEntity = reminderRepository.save(reminder);
        return reminderMapperDto.toDto(savedEntity);
    }

    @Transactional
    public void delete(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        if (!reminder.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You do not have permission to delete this reminder");
        }
        reminderRepository.deleteById(id);
    }
}
