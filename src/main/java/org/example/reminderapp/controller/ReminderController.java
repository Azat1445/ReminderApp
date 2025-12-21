package org.example.reminderapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.reminderapp.dto.ReminderCreateDto;
import org.example.reminderapp.dto.ReminderFilterDto;
import org.example.reminderapp.dto.ReminderResponseDto;
import org.example.reminderapp.dto.ReminderUpdateDto;
import org.example.reminderapp.service.ReminderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @GetMapping
    public Page<ReminderResponseDto> findAll(ReminderFilterDto filter, Pageable pageable) {
        log.info("Searching for reminders with filter {}, page: {}",
                filter, pageable);
        return reminderService.findAll(filter, pageable);
    }

    @GetMapping("/{id}")
    public ReminderResponseDto findById(@PathVariable Long id) {
        log.info("Searching for reminder with id {}", id);
        return reminderService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReminderResponseDto create(@RequestBody ReminderCreateDto createDto) {
        log.info("Creating reminder for userId: {}, title '{}'",
                createDto.getUserId(), createDto.getTitle());
        return reminderService.create(createDto);
    }

    @PutMapping("/{id}")
    public ReminderResponseDto update(@PathVariable Long id, @RequestBody ReminderUpdateDto updateDto) {
        log.info("Updating reminder with id {}", id);
        return reminderService.update(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting reminder with id {}", id);
        reminderService.delete(id);
    }
}
