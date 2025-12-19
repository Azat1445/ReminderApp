package org.example.reminderapp.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.reminderapp.dto.ReminderCreateDto;
import org.example.reminderapp.dto.ReminderFilterDto;
import org.example.reminderapp.dto.ReminderResponseDto;
import org.example.reminderapp.dto.ReminderUpdateDto;
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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final UserRepository userRepository;
    private final ReminderRepository reminderRepository;
    private final ReminderMapperDto reminderMapperDto;

    @Transactional
    public ReminderResponseDto create(ReminderCreateDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + dto.getUserId()));

        Reminder reminder = reminderMapperDto.toEntity(dto);

        reminder.setUser(user);
        reminder.setStatus(Status.PENDING);

        Reminder savedEntity = reminderRepository.save(reminder);
        return reminderMapperDto.toDto(savedEntity);
    }

    @Transactional
    public ReminderResponseDto update(Long id, ReminderUpdateDto dto) {
        Reminder reminder = reminderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reminder not found with id: " + id));
        reminderMapperDto.updateEntity(dto, reminder);

        Reminder savedEntity = reminderRepository.save(reminder);

        return reminderMapperDto.toDto(savedEntity);
    }

    @Transactional
    public void delete(Long id) {
        if (!reminderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Reminder not found with id: " + id);
        }
        reminderRepository.deleteById(id);
    }

    @Transactional
    public Page<ReminderResponseDto> findAll(ReminderFilterDto filter, Pageable pageable) {
        Specification<Reminder> specification = ReminderSpecification.withFilters(filter);

        return reminderRepository.findAll(specification,pageable)
                .map(reminderMapperDto::toDto);
    }

}
