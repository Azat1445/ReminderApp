package org.example.reminderapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.reminderapp.mapper.ReminderMapperDto;
import org.example.reminderapp.repository.ReminderRepository;
import org.example.reminderapp.service.ReminderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderRepository reminderRepository;
    private final ReminderMapperDto reminderMapperDto;

//    @GetMapping

}
